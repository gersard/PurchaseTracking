package cl.gersard.shoppingtracking.ui.scan

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.extension.dpToPx
import cl.gersard.shoppingtracking.databinding.ScanFragmentBinding
import cl.gersard.shoppingtracking.ui.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
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
        viewBinding.focusView.drawFocusBox(280.dpToPx(), 160.dpToPx())
        checkPermissions()
    }

    private fun checkPermissions() {
        if (PermissionUtil.allPermissionsGranted(requireContext(), arrayOf(REQUIRED_PERMISSION))) {
            startCamera()
        } else {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    startCamera()
                } else {
                    //TODO Show dialog
                }
            }.launch(REQUIRED_PERMISSION)
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
                .also { it.setAnalyzer(cameraExecutor, BarcodeAnalyzer(this)) }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun barcodeDetected(barcode: String) {
        if (processingBarcode.compareAndSet(false, true)) {
            Timber.d(barcode)
        }
    }

    override fun errorDetection(error: Exception) {
        Timber.e(error)
    }

    override fun onDestroyView() {
        cameraExecutor.shutdown()
        super.onDestroyView()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA

        fun newInstance() = ScanFragment()
    }

}