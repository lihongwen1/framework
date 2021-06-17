package frame.basis.jitpack.extension.widget

import android.content.Context
import android.widget.Toast

fun String.toastExpand(context: Context): Unit =
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

fun String?.safeToastExpand(context: Context?) {
    if (!isNullOrEmpty()) {
        context?.let { toastExpand(it) }
    }
}