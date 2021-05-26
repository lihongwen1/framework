package frame.basis.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

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