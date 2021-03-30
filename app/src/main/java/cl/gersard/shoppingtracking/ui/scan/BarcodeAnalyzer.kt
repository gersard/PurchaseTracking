package cl.gersard.shoppingtracking.ui.scan

import android.annotation.SuppressLint
import android.graphics.Rect
import android.graphics.RectF
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val barcodeListener: BarcodeListener,
    private val cameraWidth: Float,
    private val cameraHeight: Float,
    private val rectBoxScanner: RectF?
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    barcodes.forEach { barcode ->
                        if (barcode.rawValue?.isNotEmpty() == true && isInsideBox(
                                barcode.boundingBox,
                                image.width.toFloat(),
                                image.height.toFloat()
                            )
                        ) {
                            barcodeListener.barcodeDetected(barcode.rawValue!!)
                        }
                    }
                }
                .addOnFailureListener { barcodeListener.errorDetection(it) }
                .addOnCompleteListener { imageProxy.close() }
        }
    }

    private fun isInsideBox(boundingBoxBarcode: Rect?, imageWidth: Float, imageHeight: Float): Boolean {
        val scaleX = cameraWidth / imageHeight
        val scaleY = cameraHeight / imageWidth
        val boxBarcode = RectF(
            translateX(boundingBoxBarcode!!.left.toFloat(), scaleX),
            translateY(boundingBoxBarcode.top.toFloat(), scaleY),
            translateX(boundingBoxBarcode.right.toFloat(), scaleX),
            translateY(boundingBoxBarcode.bottom.toFloat(), scaleY)
        )
        return rectBoxScanner!!.contains(boxBarcode)
    }

    private fun translateX(x: Float, scale: Float): Float = x * scale
    private fun translateY(y: Float, scale: Float): Float = y * scale
}