package frame.basis.jitpack

import androidx.annotation.LayoutRes

abstract class BaseComponentFragment(
    @LayoutRes
    val layout: Int
) : BaseFragment(layout)