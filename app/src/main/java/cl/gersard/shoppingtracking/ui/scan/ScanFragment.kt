package cl.gersard.shoppingtracking.ui.scan

import android.Manifest
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.extension.afterLayout
import cl.gersard.shoppingtracking.databinding.ScanFragmentBinding
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.ui.MainActivity
import cl.gersard.shoppingtracking.ui.purchase.PurchaseFragment
import cl.gersard.shoppingtracking.ui.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

@AndroidEntryPoint
class ScanFragment : Fragment(), BarcodeListener {

    private var _viewBinding: ScanFragmentBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val viewModel by viewModels<ScanViewModel>()
    private var processingBarcode = AtomicBoolean(false)
    private lateinit var cameraExecutor: ExecutorService
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            showPermissionDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cameraExecutor = Executors.newSingleThreadExecutor()
        _viewBinding = ScanFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.afterLayout {
            val width = view.width * WIDTH_PERCENTAGE
            val height = view.height * HEIGHT_PERCENTAGE
            viewBinding.focusView.drawFocusBox(width, height)
            checkPermissions()

            observeProductState()
        }
    }

    private fun observeProductState() {
        viewModel.productState.observe(viewLifecycleOwner, { productState ->
            when (productState) {
                ProductState.Empty -> Toast.makeText(requireContext(), "No se encontró el producto", Toast.LENGTH_LONG).show()
                is ProductState.Error -> Toast.makeText(requireContext(), "Ocurrió un problema al buscar el producto", Toast.LENGTH_LONG).show()
                is ProductState.Success -> Toast.makeText(requireContext(), "Producto encontrado: ${productState.data.name}", Toast.LENGTH_LONG)
                    .show()
            }
            lifecycleScope.launch {
                // Delay to dont spam
                delay(2000)
                processingBarcode.set(false)
            }
        })
    }

    private fun checkPermissions() {
        if (PermissionUtil.allPermissionsGranted(requireContext(), arrayOf(REQUIRED_PERMISSION))) {
            startCamera()
        } else {
            activityResultLauncher.launch(REQUIRED_PERMISSION)
        }
    }

    private fun showPermissionDialog() {
        if (isAdded) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.title_dialog_permission_camera))
                .setMessage(getString(R.string.message_dialog_permission_camera))
                .setPositiveButton(getString(R.string.button_positive_text_dialog_permission)) { _, _ -> checkPermissions() }
                .setNegativeButton(getString(R.string.button_negative_text_dialog_permission)) { dialog, _ ->
                    dialog.dismiss()
                    requireActivity().onBackPressed()
                }
                .create()
                .show()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also { it.setSurfaceProvider(viewBinding.previewView.surfaceProvider) }

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(
                        cameraExecutor, BarcodeAnalyzer(
                            this,
                            viewBinding.previewView.width.toFloat(),
                            viewBinding.previewView.height.toFloat(),
                            viewBinding.focusView.rect
                        )
                    )
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun barcodeDetected(barcode: String) {
        if (processingBarcode.compareAndSet(false, true)) {
//            viewModel.searchProduct(barcode)
            (requireActivity() as MainActivity).changeFragment(PurchaseFragment.newInstance(barcode), false)
        }
    }

    override fun errorDetection(error: Exception) {
        Timber.e(error)
    }

    override fun onDestroyView() {
        cameraExecutor.shutdown()
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        private const val WIDTH_PERCENTAGE = 0.8f
        private const val HEIGHT_PERCENTAGE = 0.25F

        fun newInstance() = ScanFragment()
    }

}