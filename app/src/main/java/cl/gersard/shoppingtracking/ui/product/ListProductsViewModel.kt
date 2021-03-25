package cl.gersard.shoppingtracking.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.domain.Product
import cl.gersard.shoppingtracking.domain.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProductsViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {

    private var _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState get() = _loadingState

    private var _productState: MutableLiveData<List<Product>> = MutableLiveData()
    val productState get() = _productState

    fun fetchProducts() {
        viewModelScope.launch {
            _loadingState.value = true
            _productState.value = useCase.getProductsWithPurchases()
            _loadingState.value = false
        }
    }

}