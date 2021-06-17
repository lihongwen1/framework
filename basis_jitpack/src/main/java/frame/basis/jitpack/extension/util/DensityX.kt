package frame.basis.jitpack.extension.util

import android.content.Context
import android.util.TypedValue

fun Context.dp2pxExpand(dpVal: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, resources.displayMetrics).toInt()

fun Context.sp2pxExpand(spVal: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, resources.displayMetrics).toInt()

fun Context.px2dpExpand(pxVal: Float): Float =
    pxVal / resources.displayMetrics.density

fun Context.px2spExpand(pxVal: Float): Float =
    pxVal / resources.displayMetrics.scaledDensity