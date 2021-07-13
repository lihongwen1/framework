// @see androidx.core:core-ktx:version   androidx.core.view
package frame.basis.jitpack.extension.view

import android.view.View
import android.view.ViewTreeObserver

fun View.addOnPreDrawListenerExpand(action: () -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            action.invoke()
            return true
        }
    })
}

fun View.addOnGlobalLayoutListenerExpand(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            action.invoke()
        }
    })
}

fun View.postDelayedExpand(
    delayInMillis: Long,
    action: () -> Unit
): Runnable {
    val runnable = Runnable { action() }
    postDelayed(runnable, delayInMillis)
    return runnable
}

fun View.isVisibleExpand(): Boolean = visibility == View.VISIBLE

fun View.isGoneExpand(): Boolean = visibility == View.GONE

fun View.isInVisibleExpand(): Boolean = visibility == View.INVISIBLE

fun View.showExpand(): View = apply { if (!isVisibleExpand()) visibility = View.VISIBLE }

fun View.hideExpand(): View = apply { if (!isGoneExpand()) visibility = View.GONE }

fun View.invisibleExpand(): View = apply { if (!isInVisibleExpand()) visibility = View.INVISIBLE }

fun goneViews(vararg views: View?) {
    for (view in views) {
        view?.hideExpand()
    }
}

fun visibleViews(vararg views: View?) {
    for (view in views) {
        view?.showExpand()
    }
}

fun invisibleView(vararg views: View?) {
    for (view in views) {
        view?.invisibleExpand()
    }
}