package cl.gersard.shoppingtracking.ui.purchase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.domain.brand.Brand
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.market.Market
import cl.gersard.shoppingtracking.domain.market.MarketUseCase
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import cl.gersard.shoppingtracking.domain.purchase.PurchaseSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val brandUseCase: BrandUseCase,
    private val marketUseCase: MarketUseCase
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

    fun savePurchaseProduct() {
        viewModelScope.launch {
            _purchasesSaveState.value = PurchaseSaveState.Loading(true)
            delay(1000)
            _purchasesSaveState.value = PurchaseSaveState.Loading(false)
        }
    }

    fun collapseContainerProductInfo() {
        _containerProductCollapseState.value = !(_containerProductCollapseState.value ?: false)
    }


}