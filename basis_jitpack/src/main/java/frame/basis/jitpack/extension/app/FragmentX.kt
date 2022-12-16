package frame.basis.jitpack.extension.app

import android.content.ContentValues
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.annotation.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import frame.basis.jitpack.extension.content.*
import frame.basis.jitpack.extension.graphics.drawable.minimumWidthAndHeightDrawableExpand
import frame.basis.jitpack.extension.util.lazyValue
import java.io.File
import java.util.concurrent.Executor

/**
 * Fragment ViewBinding
 * <pre>
 *     class MainFragment : Fragment(R.layout.fragment_main) {
 *       private val viewBind by viewBinding(FragmentMainBinding::bind)
 *     }
 * </pre>
 * 注意传入布局Id,因为在代理getValue的时候会获取requireView
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding(noinline bindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, bindingFactory)

inline fun <reified T : ViewModel> Fragment.viewModel() = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    ViewModelProvider(this).get(T::class.java)
}

inline fun <reified T> Fragment.bundle(key: String, defaultValue: T? = null): Lazy<T?> {
    return lazyValue { arguments?.get(key) as T? ?: defaultValue }
}

inline fun <reified T> Fragment.bundleNotNull(key: String): Lazy<T> {
    return lazyValue { arguments?.get(key) as T }
}

fun Fragment.requireRunOnUiThread(action: () -> Unit): Unit =
    requireActivity().runOnUiThread { action.invoke() }

fun Fragment.runOnUiThread(action: () -> Unit): Unit? =
    activity?.runOnUiThread { action.invoke() }

fun Fragment.toastExpand(any: Any): Unit? =
    activity?.toastExpand(any.toString())

val Fragment.dataDirCompat: File?
    get() = context?.dataDirCompat

val Fragment.obbDirsCompat: Array<File>
    get() = requireContext().obbDirsCompat

val Fragment.externalCacheDirsCompat: Array<File>
    get() = requireContext().externalCacheDirsCompat

val Fragment.noBackupFilesDirCompat: File?
    get() = context?.noBackupFilesDirCompat

val Fragment.codeCacheDirCompat: File
    get() = requireContext().codeCacheDirCompat

val Fragment.isDeviceProtectedStorageCompat: Boolean
    get() = requireContext().isDeviceProtectedStorageCompat

val Fragment.mainExecutorCompat: Executor
    get() = requireContext().mainExecutorCompat

val Fragment.isLandscapeExpand: Boolean
    get() = requireContext().isLandscapeExpand

fun Fragment.externalFilesDirsExpand(type: String?): Array<File> =
    requireContext().externalFilesDirsExpand(type)

fun Fragment.drawableExpand(@DrawableRes id: Int): Drawable? =
    context?.drawableExpand(id)

fun Fragment.colorStateListExpand(@ColorRes id: Int): ColorStateList? =
    context?.colorStateListExpand(id)

fun Fragment.colorExpand(@ColorRes id: Int): Int =
    requireContext().colorExpand(id)

fun Fragment.checkPermissionExpand(permission: String): Int =
    requireContext().checkPermissionExpand(permission)

fun Fragment.booleanExpand(@BoolRes id: Int): Boolean =
    requireContext().booleanExpand(id)

fun Fragment.intExpand(@IntegerRes id: Int): Int =
    requireContext().intExpand(id)

@RequiresApi(Build.VERSION_CODES.Q)
fun Fragment.floatExpand(@DimenRes id: Int): Float =
    requireContext().floatExpand(id)

fun Fragment.textExpand(@StringRes id: Int): CharSequence =
    requireContext().textExpand(id)

fun Fragment.stringExpand(@StringRes id: Int): String =
    requireContext().stringExpand(id)

fun Fragment.stringExpand(@StringRes id: Int, vararg formatArgs: Any): String =
    requireContext().stringExpand(id, *formatArgs)

fun Fragment.textArrayExpand(@ArrayRes id: Int): Array<CharSequence> =
    requireContext().textArrayExpand(id)

fun Fragment.stringArrayExpand(@ArrayRes id: Int): Array<String> =
    requireContext().stringArrayExpand(id)

fun Fragment.intArrayExpand(@ArrayRes id: Int): IntArray =
    requireContext().intArrayExpand(id)

fun Fragment.dimensionExpand(@DimenRes id: Int): Float =
    requireContext().dimensionExpand(id)

fun Fragment.dimensionPixelOffsetExpand(@DimenRes id: Int): Int =
    requireContext().dimensionPixelOffsetExpand(id)

fun Fragment.dimensionPixelSizeExpand(@DimenRes id: Int): Int =
    requireContext().dimensionPixelSizeExpand(id)

fun Fragment.quantityTextExpand(@PluralsRes id: Int, quantity: Int): CharSequence =
    requireContext().quantityTextExpand(id, quantity)

fun Fragment.quantityStringExpand(
    @PluralsRes id: Int,
    quantity: Int,
    vararg formatArgs: Any,
): CharSequence = requireContext().quantityStringExpand(id, quantity, *formatArgs)

fun Fragment.quantityStringExpand(@PluralsRes id: Int, quantity: Int): String =
    requireContext().quantityStringExpand(id, quantity)

@RequiresApi(Build.VERSION_CODES.O)
fun Fragment.fontExpand(@FontRes id: Int): Typeface =
    requireContext().fontExpand(id)

fun Fragment.insertImageUriExpand(contentValues: ContentValues): Uri? =
    context?.insertImageUriExpand(contentValues)

fun Fragment.insertVideoUriExpand(contentValues: ContentValues): Uri? =
    context?.insertVideoUriExpand(contentValues)

fun Fragment.appIntentExpand(packageName: String): Intent? =
    context?.packageManager?.getLaunchIntentForPackage(packageName)

fun Fragment.minimumDrawableExpand(@DrawableRes id: Int, @ColorInt color: Int): Drawable? =
    drawableExpand(id)?.minimumWidthAndHeightDrawableExpand(color)

fun Fragment.moveToNextToIdExpand(uri: Uri): Boolean =
    requireContext().moveToNextToIdExpand(uri)

fun Fragment.moveToNextExpand(uri: Uri, name: String): Boolean =
    requireContext().moveToNextExpand(uri, name)

fun Fragment.findIdByUriExpand(uri: Uri): Long =
    requireContext().findIdByUriExpand(uri)

fun Fragment.findPathByUriExpand(uri: Uri): String? =
    context?.findPathByUriExpand(uri)

fun Fragment.getJsonExpand(fileName: String): String =
    requireContext().getJsonExpand(fileName)