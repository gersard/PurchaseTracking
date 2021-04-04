package cl.gersard.shoppingtracking.ui.purchase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.core.DateUtils
import cl.gersard.shoppingtracking.core.SimpleItemInfo
import cl.gersard.shoppingtracking.core.extension.*
import cl.gersard.shoppingtracking.databinding.PurchaseFragmentBinding
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.purchase.PurchaseSaveState
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.*

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
        Timber.d("On View Created")
        observeBrands()
        observeMarkets()
        observeProductState()
        observePurchaseState()
        observeContainersCollapse()
        observeErrors()

        viewModel.fetchBrands()
        viewModel.fetchMarkets()

        val barcodeProduct = arguments?.getString(BARCODE_PRODUCT, "")!!
        viewModel.searchProduct(barcodeProduct)

        viewBinding.etPurchaseDate.setText(LocalDateTime.now().format(DateUtils.FORMAT_PURCHASE_FORM))
        viewBinding.etProductBarcode.setText(barcodeProduct)

        viewBinding.ibActionProductInfo.setOnClickListener { viewModel.collapseContainerProductInfo() }
        viewBinding.btnSaveProductPurchase.setOnClickListener { savePurchase() }
        viewBinding.atvProductBrand.setOnTouchListener(this)
        viewBinding.atvPurchaseMarket.setOnTouchListener(this)
        viewBinding.etPurchaseDate.setOnTouchListener(this)
    }

    private fun savePurchase() {
        with(viewBinding) {
            viewModel.savePurchaseProduct(
                atvProductBrand.text(),
                atvPurchaseMarket.text(),
                etProductName.text(),
                etProductDescription.text(),
                etProductBarcode.text(),
                etProductNote.text(),
                etPurchaseTotal.text().toIntOrNull(),
                etPurchaseQuantity.text().toIntOrNull(),
                DateUtils.parseDate(etPurchaseDate.text(), DateUtils.FORMAT_PURCHASE_FORM),
                cbPurchaseHadDiscount.isChecked,
                etPurchaseNote.text()
            )
        }
    }

    private fun observeErrors() {
        viewModel.errorState.observe(viewLifecycleOwner, { messageRes ->
            Snackbar.make(viewBinding.root, messageRes, Snackbar.LENGTH_LONG)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color_error))
                .show()
        })
    }

    private fun observePurchaseState() {
        viewModel.purchasesSaveState.observe(viewLifecycleOwner, { purchaseState ->
            when (purchaseState) {
                is PurchaseSaveState.Error -> TODO()
                is PurchaseSaveState.Loading -> manageProgress(purchaseState.isLoading)
                PurchaseSaveState.Success -> {
                    Toast.makeText(requireActivity(), getString(R.string.purchase_saved_successfully), Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
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
            val adapter = SimpleItemAdapter(requireContext(), R.layout.row_text_simple, markets, this)
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
            requireActivity().hideKeyboard()

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
                ProductState.Empty -> {
                    Timber.d("Product empty")
                    Snackbar.make(viewBinding.root, getString(R.string.product_not_founded), Snackbar.LENGTH_LONG)
                        .show()
                }
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
            etProductNote.setText(product.note)
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
            viewBinding.etPurchaseDate.setText(offsetDateTime.format(DateUtils.FORMAT_PURCHASE_FORM))
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