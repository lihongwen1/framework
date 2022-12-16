package frame.basis.jitpack.extension.version

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

fun Int.minVersionExpand(): Boolean = Build.VERSION.SDK_INT >= this

fun Int.maxVersionExpand(): Boolean = Build.VERSION.SDK_INT <= this

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun hasLMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 22

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
fun hasMExpand(): Boolean = Build.VERSION.SDK_INT >= 23

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
fun hasNExpand(): Boolean = Build.VERSION.SDK_INT >= 24

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
fun hasNMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 25

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun hasOExpand(): Boolean = Build.VERSION.SDK_INT >= 26

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
fun hasOMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 27

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun hasPExpand(): Boolean = Build.VERSION.SDK_INT >= 28

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
fun hasQExpand(): Boolean = Build.VERSION.SDK_INT >= 29

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun hasRExpand(): Boolean = Build.VERSION.SDK_INT >= 30