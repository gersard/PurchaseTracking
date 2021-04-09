package cl.gersard.shoppingtracking.ui.purchase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.R
import cl.gersard.shoppingtracking.domain.brand.Brand
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.market.Market
import cl.gersard.shoppingtracking.domain.market.MarketUseCase
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductInsertUpdate
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import cl.gersard.shoppingtracking.domain.purchase.PurchaseSaveState
import cl.gersard.shoppingtracking.domain.purchase.PurchaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val brandUseCase: BrandUseCase,
    private val marketUseCase: MarketUseCase,
    private val purchaseUseCase: PurchaseUseCase
) : ViewModel() {

    // PRODUCT
    private var _productState: MutableLiveData<ProductState<Product>> = MutableLiveData()
    val productState get() = _productState

    // LIST OF BRANDS
    private var _brandsState: MutableLiveData<List<Brand>> = MutableLiveData()
    val brandsState get() = _brandsState

    // LIST OF MARKETS
    private var _marketsState: MutableLiveData<List<Market>> = MutableLiveData()
    val marketsState get() = _marketsState

    // STATE OF CONTAINER PRODUCT INFO
    private var _containerProductCollapseState: MutableLiveData<Boolean> = MutableLiveData()
    val containerProductCollapseState get() = _containerProductCollapseState

    // STATE OF PURCHASE'S SAVE
    private var _purchasesSaveState: MutableLiveData<PurchaseSaveState> = MutableLiveData()
    val purchasesSaveState get() = _purchasesSaveState

    // STATE OF ERROR INSERTING PURCHASE
    private var _errorState: MutableLiveData<Int> = MutableLiveData()
    val errorState get() = _errorState

    fun searchProduct(barcodeProduct: String) {
        viewModelScope.launch {
            productState.value = productUseCase.searchProduct(barcodeProduct)
        }
    }

    fun fetchBrands() {
        viewModelScope.launch {
            _brandsState.value = brandUseCase.getBrands()
        }
    }

    fun fetchMarkets() {
        viewModelScope.launch {
            _marketsState.value = marketUseCase.getMarkets()
        }
    }

    fun savePurchaseProduct(
        brandName: String, marketName: String, prodName: String, prodDesc: String, prodBarcode: String, prodNote: String,
        total: Int?, quantity: Int?, date: LocalDate, hasDiscount: Boolean, purchaseNote: String
    ) {
        viewModelScope.launch {
            if (hasFormError(prodName, prodBarcode, total, quantity, date)) return@launch

            _purchasesSaveState.value = PurchaseSaveState.Loading(true)
            // Brand
            val brand = brandUseCase.getBrand(brandName)
            val brandId = brand?.id ?: brandUseCase.insertBrand(brandName)

            // Market
            val market = marketUseCase.getMarket(marketName)
            val marketId = market?.id ?: marketUseCase.insertMarket(marketName)

            // Product
            val productId = if (productState.value != null && productState.value is ProductState.Success) {
                val product = (productState.value as ProductState.Success).data
                productUseCase.updateProduct(ProductInsertUpdate(product.id, prodBarcode, prodName, prodDesc, brandId, prodNote))
                product.id
            } else {
                productUseCase.insertProduct(0, prodName, prodDesc, prodBarcode, brandId, prodNote)
            }

            // PURCHASE
            val purchaseId = purchaseUseCase.insertPurchase(productId, total!! / quantity!!, quantity, date, marketId, hasDiscount, purchaseNote)

            _purchasesSaveState.value = PurchaseSaveState.Loading(false)
            _purchasesSaveState.value = PurchaseSaveState.Success
        }
    }

    private fun hasFormError(prodName: String, prodBarcode: String, total: Int?, quantity: Int?, date: LocalDate?): Boolean {
        if (prodName.isEmpty()) {
            _errorState.value = R.string.error_product_name_required
            return true
        }
        if (prodBarcode.isEmpty()) {
            _errorState.value = R.string.error_product_barcode_required
            return true
        }
        if (total == null) {
            _errorState.value = R.string.error_purchase_total_required
            return true
        }
        if (total <= 0) {
            _errorState.value = R.string.error_purchase_total_negative
            return true
        }
        if (quantity == null) {
            _errorState.value = R.string.error_purchase_quantity_required
            return true
        }
        if (quantity <= 0) {
            _errorState.value = R.string.error_purchase_quantity_negative
            return true
        }
        if (date == null) {
            _errorState.value = R.string.error_purchase_date_required
            return true
        }

        return false
    }

    fun collapseContainerProductInfo() {
        _containerProductCollapseState.value = !(_containerProductCollapseState.value ?: false)
    }


}