package cl.gersard.shoppingtracking.domain.purchase

sealed class PurchaseSaveState {
    object Success : PurchaseSaveState()
    data class Loading(val isLoading: Boolean) : PurchaseSaveState()
    data class Error(var error: String) : PurchaseSaveState()

}
