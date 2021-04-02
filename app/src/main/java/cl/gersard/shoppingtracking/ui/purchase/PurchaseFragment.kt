package cl.gersard.shoppingtracking.ui.purchase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.databinding.PurchaseFragmentBinding
import cl.gersard.shoppingtracking.domain.product.ProductState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseFragment : Fragment() {

    private val viewModel by viewModels<PurchaseViewModel>()
    private var _viewBinding: PurchaseFragmentBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = PurchaseFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProductState()
        viewModel.searchProduct(arguments?.getString(BARCODE_PRODUCT, "")!!)
    }

    private fun observeProductState() {
        viewModel.productState.observe(viewLifecycleOwner, { productState ->
            when (productState) {
                ProductState.Empty -> Snackbar.make(viewBinding.root, "Producto no encontrado, rellena sus datos para crearlo", Snackbar.LENGTH_LONG)
                    .show()
                is ProductState.Error -> TODO()
                is ProductState.Success -> Toast.makeText(requireContext(), "Producto: ${productState.data}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        private const val BARCODE_PRODUCT = "barcode_product"

        fun newInstance(barcodeProduct: String) = PurchaseFragment().apply {
            arguments = Bundle().apply {
                putString(BARCODE_PRODUCT, barcodeProduct)
            }
        }
    }

}