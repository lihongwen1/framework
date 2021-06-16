package frame.basis.jitpack.extension.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import frame.basis.jitpack.extension.lazyValue

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