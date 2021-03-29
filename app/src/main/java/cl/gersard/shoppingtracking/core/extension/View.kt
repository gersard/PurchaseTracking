package cl.gersard.shoppingtracking.core.extension

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewTreeObserver


fun View.visible(withAnimation: Boolean = false, durationMs: Long = 300) {
    if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
            .setDuration(durationMs)
            .start()
    }
}

fun View.gone(withAnimation: Boolean = false, durationMs: Long = 300) {
    if (this.visibility != View.GONE) this.visibility = View.GONE
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            .setDuration(durationMs)
            .start()
    }
}

fun View.invisible(withAnimation: Boolean = false, durationMs: Long = 300) {
    if (this.visibility != View.INVISIBLE) this.visibility = View.INVISIBLE
    if (withAnimation) {
        ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            .setDuration(durationMs)
            .start()
    }
}

fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

fun View.afterLayout(laidOut: (View) -> Unit) {
    if(isLaidOut) {
        laidOut(this)
    } else {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                laidOut(this@afterLayout)
            }
        })
    }
}