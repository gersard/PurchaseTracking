package cl.gersard.shoppingtracking.ui.product

import androidx.lifecycle.ViewModel
import cl.gersard.shoppingtracking.domain.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListProductsViewModel @Inject constructor(useCase: ProductUseCase) : ViewModel() {
    // TODO: Implement the ViewModel
}