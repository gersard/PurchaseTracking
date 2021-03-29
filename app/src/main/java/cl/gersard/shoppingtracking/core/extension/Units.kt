package cl.gersard.shoppingtracking.core.extension

import android.content.res.Resources

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density)