package cl.gersard.shoppingtracking.ui.scan

interface BarcodeListener {
    fun barcodeDetected(barcode: String)
    fun errorDetection(error: Exception)
}