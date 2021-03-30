package cl.gersard.shoppingtracking.ui.scan

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScanViewModel : ViewModel() {

    fun searchProduct(barcode: String) {
        Timber.d(barcode)
    }


}