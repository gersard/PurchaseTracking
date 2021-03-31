package cl.gersard.shoppingtracking.ui.scan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {

    private var _productState: MutableLiveData<ProductState<Product>> = MutableLiveData()
    val productState get() = _productState

    fun searchProduct(barcode: String) {
        viewModelScope.launch {
            productState.value = useCase.searchProduct(barcode)
        }
    }


}