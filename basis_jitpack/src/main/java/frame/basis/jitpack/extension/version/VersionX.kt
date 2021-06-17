package frame.basis.jitpack.extension.version

import android.os.Build

fun Int.minVersionExpand(): Boolean = Build.VERSION.SDK_INT >= this

fun Int.maxVersionExpand(): Boolean = Build.VERSION.SDK_INT <= this

fun hasKExpand(): Boolean = Build.VERSION.SDK_INT >= 19

fun hasLExpand(): Boolean = Build.VERSION.SDK_INT >= 21

fun hasLMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 22

fun hasMExpand(): Boolean = Build.VERSION.SDK_INT >= 23

fun hasNExpand(): Boolean = Build.VERSION.SDK_INT >= 24

fun hasNMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 25

fun hasOExpand(): Boolean = Build.VERSION.SDK_INT >= 26

fun hasOMr1Expand(): Boolean = Build.VERSION.SDK_INT >= 27

fun hasPExpand(): Boolean = Build.VERSION.SDK_INT >= 28

fun hasQExpand(): Boolean = Build.VERSION.SDK_INT >= 29

fun hasRExpand(): Boolean = Build.VERSION.SDK_INT >= 30