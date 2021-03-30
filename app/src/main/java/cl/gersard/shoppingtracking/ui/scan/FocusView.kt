package cl.gersard.shoppingtracking.ui.scan

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import cl.gersard.shoppingtracking.R

class FocusView : View {

    private val path = Path()
    private lateinit var transparentPaint: Paint
    private lateinit var whiteStrokePaint: Paint
    private var radius: Float = 10f
    var rect: RectF? = null

    constructor(context: Context?) : super(context) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initPaints()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaints()
    }

    fun drawFocusBox(widthBox: Float, heightBox: Float) {
        rect = RectF().apply {
            left = ((width / 2) - (widthBox / 2))
            right = (width / 2) + (widthBox / 2)
            top = (height / 2) - (heightBox / 2)
            bottom = (height / 2) + (heightBox / 2)
        }
        invalidate()
    }

    private fun initPaints() {
        transparentPaint = Paint().apply {
            color = Color.TRANSPARENT
            strokeWidth = 10f
        }

        whiteStrokePaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 6f
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (rect == null || canvas == null) return
        path.reset()
        path.addRoundRect(rect!!, radius, radius, Path.Direction.CW)
        path.fillType = Path.FillType.INVERSE_EVEN_ODD

        canvas.drawPath(path, transparentPaint)
        canvas.drawRoundRect(rect!!, radius, radius, whiteStrokePaint)
        canvas.clipPath(path)
        canvas.drawColor(ContextCompat.getColor(context, R.color.opacity_camera_scanner))
    }

}