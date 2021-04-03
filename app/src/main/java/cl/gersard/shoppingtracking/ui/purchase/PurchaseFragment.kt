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
import cl.gersard.shoppingtracking.core.DateFormats
import cl.gersard.shoppingtracking.core.SimpleItemInfo
import cl.gersard.shoppingtracking.core.extension.format
import cl.gersard.shoppingtracking.core.extension.gone
import cl.gersard.shoppingtracking.core.extension.visible
import cl.gersard.shoppingtracking.databinding.PurchaseFragmentBinding
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.purchase.PurchaseSaveState
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class PurchaseFragment : Fragment(), View.OnTouchListener, SimpleItemAdapter.SimpleItemListener {

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
        observePurchaseState()
        observeContainersCollapse()

        viewModel.fetchBrands()
        viewModel.fetchMarkets()
        val barcodeProduct = arguments?.getString(BARCODE_PRODUCT, "")!!
        viewModel.searchProduct(barcodeProduct)

        viewBinding.etPurchaseDate.setText(LocalDateTime.now().format(DateFormats.PURCHASE_FORM))
        viewBinding.etProductBarcode.setText(barcodeProduct)

        viewBinding.ibActionProductInfo.setOnClickListener { viewModel.collapseContainerProductInfo() }
        viewBinding.btnSaveProductPurchase.setOnClickListener { viewModel.savePurchaseProduct() }
        viewBinding.atvProductBrand.setOnTouchListener(this)
        viewBinding.atvPurchaseMarket.setOnTouchListener(this)
        viewBinding.etPurchaseDate.setOnTouchListener(this)
    }

    private fun observePurchaseState() {
        viewModel.purchasesSaveState.observe(viewLifecycleOwner, { purchaseState ->
            when (purchaseState) {
                is PurchaseSaveState.Error -> TODO()
                is PurchaseSaveState.Loading -> manageProgress(purchaseState.isLoading)
                PurchaseSaveState.Success -> TODO()
            }
        })
    }

    private fun manageProgress(isLoading: Boolean) {
        with(viewBinding) {
            if (isLoading) {
                pbSavePurchase.visible(true)
                btnSaveProductPurchase.gone(true)
            } else {
                pbSavePurchase.gone(true)
                btnSaveProductPurchase.visible(true)
            }
        }
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
            val adapter = SimpleItemAdapter(requireContext(), R.layout.row_text_simple, brands, this)
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v?.id) {
            viewBinding.atvProductBrand.id -> {
                viewBinding.atvProductBrand.showDropDown()
            }
            viewBinding.atvPurchaseMarket.id -> {
                viewBinding.atvPurchaseMarket.showDropDown()
            }
            viewBinding.etPurchaseDate.id -> {
                if (event?.action != MotionEvent.ACTION_UP) return true
                showDatePicker()
            }
        }
        return false
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.hint_purchase_date))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "")

        datePicker.addOnPositiveButtonClickListener {
            val offsetDateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
                .withOffsetSameInstant(ZoneOffset.UTC)
            viewBinding.etPurchaseDate.setText(offsetDateTime.format(DateFormats.PURCHASE_FORM))
        }
    }

    override fun onSimpleItemClick(type: Int, simpleItemInfo: SimpleItemInfo) {
        when (type) {
            SimpleItemInfo.TYPES.BRAND -> {
                viewBinding.atvProductBrand.setText(simpleItemInfo.name(), false)
                viewBinding.atvProductBrand.dismissDropDown()
            }
            SimpleItemInfo.TYPES.MARKET -> {
                viewBinding.atvPurchaseMarket.setText(simpleItemInfo.name(), false)
                viewBinding.atvPurchaseMarket.dismissDropDown()
            }
        }
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