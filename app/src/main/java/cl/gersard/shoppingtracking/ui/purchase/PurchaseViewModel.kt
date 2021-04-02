package cl.gersard.shoppingtracking.ui.purchase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.domain.brand.Brand
import cl.gersard.shoppingtracking.domain.brand.BrandUseCase
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val brandUseCase: BrandUseCase
) : ViewModel() {

    private var _productState: MutableLiveData<ProductState<Product>> = MutableLiveData()
    val productState get() = _productState


    private var _brandsState: MutableLiveData<List<Brand>> = MutableLiveData()
    val brandsState get() = _brandsState

    private var _containerProductCollapseState: MutableLiveData<Boolean> = MutableLiveData()
    val containerProductCollapseState get() = _containerProductCollapseState

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

    fun collapseContainerProductInfo() {
        _containerProductCollapseState.value = !(_containerProductCollapseState.value ?: false)
    }

}