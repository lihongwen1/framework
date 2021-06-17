package frame.basis.jitpack.extension.app

import android.media.MediaScannerConnection
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import frame.basis.jitpack.extension.net.filePathExpand
import frame.basis.jitpack.extension.util.lazyValue

/**
 * Activity ViewBinding
 *
 * private val viewBind by viewBinding(ActivityBinding::inflate)
 * viewBind.viewName
 * 注意传入布局Id,默认获取的是activity的根View
 */
inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (View) -> T
) = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    bindingInflater.invoke(findViewById<ViewGroup>(android.R.id.content).getChildAt(0))
}

/**
 * safe:不需要主动去获取View
 * 问题:没有调用之前不会主动去setContentView，建议尽快初始化ViewBinding
 */
inline fun <reified T : ViewBinding> AppCompatActivity.viewBindingInflater(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    bindingInflater.invoke(layoutInflater).apply { setContentView(root) }
}

//inline fun <reified VB : ViewBinding> AppCompatActivity.inflate() = lazy {
//    viewBindingInflater<VB>(layoutInflater).apply { setContentView(root) }
//}
//
//inline fun <reified VB : ViewBinding> viewBindingInflater(layoutInflater: LayoutInflater) =
//    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
//        .invoke(null, layoutInflater) as VB

inline fun <reified T : ViewModel> AppCompatActivity.viewModel() =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        ViewModelProvider(this).get(T::class.java)
    }

inline fun <reified T> AppCompatActivity.bundle(key: String, defaultValue: T? = null): Lazy<T?> {
    return lazyValue { intent.extras?.get(key) as T? ?: defaultValue }
}

inline fun <reified T> AppCompatActivity.bundleNotNull(key: String): Lazy<T> {
    return lazyValue { intent.extras?.get(key) as T }
}


fun AppCompatActivity.scanFileExpand(uri: Uri, action: (uri: Uri) -> Unit) {
    scanFileExpand(uri.filePathExpand(this), action)
}

fun AppCompatActivity.scanFileExpand(path: String, action: (uri: Uri) -> Unit) {
    MediaScannerConnection.scanFile(this, arrayOf(path), null) { _: String?, uri: Uri? ->
        runOnUiThread {
            uri ?: return@runOnUiThread
            action.invoke(uri)
        }
    }
}

inline fun <reified T : Fragment> AppCompatActivity.findFragment(tag: String): T? {
    return supportFragmentManager.findFragmentByTag(tag) as? T
}

inline fun <reified T : Fragment> AppCompatActivity.findFragment(): T? {
    return findFragment(T::class.java.simpleName) as? T
}

fun AppCompatActivity.fragmentsExpand(): MutableList<Fragment> =
    supportFragmentManager.fragments

fun AppCompatActivity.findFragmentByTagExpand(tag: String, of: (fragment: Fragment?) -> Unit) {
    of.invoke(supportFragmentManager.findFragmentByTag(tag))
}

fun AppCompatActivity.findFragmentByIdExpand(@IdRes id: Int, of: (fragment: Fragment?) -> Unit) {
    of.invoke(supportFragmentManager.findFragmentById(id))
}

fun <T : Fragment> AppCompatActivity.findFragmentByTagExpand(
    tag: String,
    ifNone: (String) -> T
): T = supportFragmentManager.findFragmentByTag(tag) as T? ?: ifNone(tag)

fun AppCompatActivity.showRunOnCommitExpand(
    fragment: Fragment,
    runnable: Runnable
): FragmentTransaction =
    supportFragmentManager.beginTransaction().show(fragment).runOnCommit(runnable)

fun AppCompatActivity.showFragmentExpand(
    fragmentType: FragmentType = FragmentType.COMMIT_ALLOWING_STATE_LOSS,
    fragment: Fragment
) = supportFragmentManager.beginTransaction().show(fragment).commitExpand(fragmentType)

fun AppCompatActivity.hideFragmentExpand(
    fragmentType: FragmentType = FragmentType.COMMIT_ALLOWING_STATE_LOSS,
    fragment: Fragment
) = supportFragmentManager.beginTransaction().hide(fragment).commitExpand(fragmentType)

fun AppCompatActivity.addFragmentExpand(
    id: Int,
    fragmentType: FragmentType = FragmentType.COMMIT_ALLOWING_STATE_LOSS,
    fragment: Fragment
) = supportFragmentManager.beginTransaction().add(id, fragment, fragment.javaClass.simpleName)
    .commitExpand(fragmentType)

fun AppCompatActivity.replaceFragmentExpand(
    id: Int,
    fragmentType: FragmentType = FragmentType.COMMIT_ALLOWING_STATE_LOSS,
    fragment: Fragment
) = supportFragmentManager.beginTransaction().replace(id, fragment, fragment.javaClass.simpleName)
    .commitExpand(fragmentType)

@Suppress("NOTHING_TO_INLINE")
internal inline fun FragmentTransaction.commitExpand(fragmentType: FragmentType) {
    when (fragmentType) {
        FragmentType.COMMIT -> commit()
        FragmentType.COMMIT_ALLOWING_STATE_LOSS -> commitAllowingStateLoss()
        FragmentType.NOW -> commitNow()
        FragmentType.NOW_ALLOWING_STATE_LOSS -> commitNowAllowingStateLoss()
    }
}

enum class FragmentType {
    COMMIT,
    COMMIT_ALLOWING_STATE_LOSS,
    NOW,
    NOW_ALLOWING_STATE_LOSS
}