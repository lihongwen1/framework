package frame.basis.opensource.permission

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions

fun AppCompatActivity.requestPermission(
    permissions: List<String>,
    action: PermissionDsl.() -> Unit
) {
    PermissionDsl().also(action).build(activity = this, permissions = permissions)
}

fun Fragment.requestPermission(
    permissions: List<String>,
    action: PermissionDsl.() -> Unit
) {
    PermissionDsl().also(action).build(fragment = this, permissions = permissions)
}

class PermissionDsl {
    private var granted: ((permissions: List<String>, all: Boolean) -> Unit)? = null
    private var denied: ((permissions: List<String>, never: Boolean) -> Unit)? = null

    fun granted(granted: (permissions: List<String>, all: Boolean) -> Unit) {
        this.granted = granted
    }

    fun denied(denied: (permissions: List<String>, never: Boolean) -> Unit) {
        this.denied = denied
    }

    internal fun build(
        activity: AppCompatActivity? = null,
        fragment: Fragment? = null,
        permissions: List<String>
    ) {
        PermissionX.request(
            activity = activity,
            fragment = fragment,
            permissions = permissions,
            callback = object : PermissionX.OnPermissionXCallback {
                override fun onDenied(permissions: List<String>, never: Boolean) {
                    denied?.invoke(permissions, never)
                }

                override fun onGranted(permissions: List<String>, all: Boolean) {
                    granted?.invoke(permissions, all)
                }
            })
    }
}

object PermissionX {

    interface OnPermissionXCallback {
        fun onGranted(permissions: List<String>, all: Boolean)
        fun onDenied(permissions: List<String>, never: Boolean)
    }

    fun request(
        activity: Activity? = null,
        fragment: Fragment? = null,
        permissions: List<String>,
        callback: OnPermissionXCallback
    ) {
        val permission = if (activity != null) {
            XXPermissions.with(activity)
        } else {
            XXPermissions.with(fragment)
        }
        permission.permission(permissions)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                    callback.onGranted(permissions ?: emptyList(), all)
                }

                override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                    callback.onDenied(permissions ?: emptyList(), never)
                }
            })
    }

}