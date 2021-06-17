package frame.basis.jitpack.extension.os.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.checkSelfPermissionExpand(name: String) =
    ContextCompat.checkSelfPermission(this, name) == PackageManager.PERMISSION_GRANTED

fun Context.checkCameraPermissionExpand() =
    checkSelfPermissionExpand(Manifest.permission.CAMERA)

fun Context.checkWritePermissionExpand() =
    checkSelfPermissionExpand(Manifest.permission.WRITE_EXTERNAL_STORAGE)

fun Fragment.checkCameraPermissionExpand() =
    requireContext().checkSelfPermissionExpand(Manifest.permission.CAMERA)

fun Fragment.checkWritePermissionExpand() =
    requireContext().checkSelfPermissionExpand(Manifest.permission.WRITE_EXTERNAL_STORAGE)