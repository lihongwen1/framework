package frame.basis.jitpack.extension.util

import android.annotation.SuppressLint
import android.os.Environment

@SuppressLint("UsableSpace")
fun storageSizeExpand(): Long {
    return Environment.getExternalStorageDirectory().usableSpace
}


