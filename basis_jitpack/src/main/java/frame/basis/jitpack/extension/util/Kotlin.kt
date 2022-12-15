package frame.basis.jitpack.extension.util

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Environment
import android.view.WindowManager

@SuppressLint("UsableSpace")
fun storageSize(): Long {
    return Environment.getExternalStorageDirectory().usableSpace
}

fun Activity.fullScreen() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.noFullScreen() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
}
