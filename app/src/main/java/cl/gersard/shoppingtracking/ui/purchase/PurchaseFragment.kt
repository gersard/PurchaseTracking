package cl.gersard.shoppingtracking.ui.purchase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.extension.gone
import cl.gersard.shoppingtracking.core.extension.visible
import cl.gersard.shoppingtracking.databinding.PurchaseFragmentBinding
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseFragment : Fragment(), View.OnTouchListener {

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBrands()
        observeMarkets()
        observeProductState()
        observeContainersCollapse()

        viewModel.fetchBrands()
        viewModel.fetchMarkets()
        viewModel.searchProduct(arguments?.getString(BARCODE_PRODUCT, "")!!)

        viewBinding.ibActionProductInfo.setOnClickListener { viewModel.collapseContainerProductInfo() }
        viewBinding.atvProductBrand.setOnTouchListener(this)
        viewBinding.atvPurchaseMarket.setOnTouchListener(this)
    }

    private fun observeMarkets() {
        viewModel.marketsState.observe(viewLifecycleOwner, { markets ->
            val marketsName = markets.map { it.name }
            val adapter = ArrayAdapter(requireContext(), R.layout.row_text_simple, marketsName)
            viewBinding.atvPurchaseMarket.setAdapter(adapter)
        })
    }

    private fun observeBrands() {
        viewModel.brandsState.observe(viewLifecycleOwner, { brands ->
            val brandsName = brands.map { it.name }
            val adapter = ArrayAdapter(requireContext(), R.layout.row_text_simple, brandsName)
            viewBinding.atvProductBrand.setAdapter(adapter)
        })
    }

    private fun observeContainersCollapse() {
        viewModel.containerProductCollapseState.observe(viewLifecycleOwner, {
            if (it) collapseProductInfo() else expandProductInfo()
        })
    }

    private fun expandProductInfo() {
        with(viewBinding) {
            // Hide the views, just show the name
            etProductDescription.visible(true)
            atvProductBrand.visible(true)
            etProductBarcode.visible(true)
            etProductNote.visible(true)

            // Rotate icon button
            if (ibActionProductInfo.rotation != 0f) ibActionProductInfo.rotation = 0f
        }
    }

    private fun collapseProductInfo() {
        with(viewBinding) {
            // Hide the views, just show the name
            etProductDescription.gone(true)
            atvProductBrand.gone(true)
            etProductBarcode.gone(true)
            etProductNote.gone(true)

            // Rotate icon button
            if (ibActionProductInfo.rotation != 180f) ibActionProductInfo.rotation = 180f
        }
    }

    private fun observeProductState() {
        viewModel.productState.observe(viewLifecycleOwner, { productState ->
            when (productState) {
                ProductState.Empty -> Snackbar.make(viewBinding.root, "Producto no encontrado, rellena sus datos para crearlo", Snackbar.LENGTH_LONG)
                    .show()
                is ProductState.Error -> TODO()
                is ProductState.Success -> {
                    loadProductInfo(productState.data)
                    viewModel.collapseContainerProductInfo()
                }
            }
        })
    }

    private fun loadProductInfo(product: Product) {
        with(viewBinding) {
            etProductName.setText(product.name)
            etProductDescription.setText(product.description)
            atvProductBrand.setText(product.brand.name, false)
            etProductBarcode.setText(product.barcode)
            etProductNote.setText(product.name)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        when (v?.id) {
            viewBinding.atvProductBrand.id -> {
                viewBinding.atvProductBrand.showDropDown()
            }
            viewBinding.atvPurchaseMarket.id -> {
                viewBinding.atvPurchaseMarket.showDropDown()
            }
        }
        return false
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