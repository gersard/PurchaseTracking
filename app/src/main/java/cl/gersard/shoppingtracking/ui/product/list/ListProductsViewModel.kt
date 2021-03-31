package cl.gersard.shoppingtracking.ui.product.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gersard.shoppingtracking.domain.product.Product
import cl.gersard.shoppingtracking.domain.product.ProductState
import cl.gersard.shoppingtracking.domain.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProductsViewModel @Inject constructor(private val useCase: ProductUseCase) : ViewModel() {

    private var _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState get() = _loadingState

    private var _productState: MutableLiveData<ProductState<List<Product>>> = MutableLiveData()
    val productState get() = _productState

    fun fetchProducts() {
        viewModelScope.launch {
            _loadingState.value = true
            val products = useCase.getProductsWithPurchases()
            _productState.value = if (products.isNotEmpty()) ProductState.Success(products) else ProductState.Empty
            _loadingState.value = false
        }
    }

}