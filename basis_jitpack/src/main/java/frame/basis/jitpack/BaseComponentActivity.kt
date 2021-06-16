package frame.basis.jitpack

import androidx.annotation.LayoutRes

abstract class BaseComponentActivity(
    @LayoutRes
    val layout: Int = NO_LAYOUT
) : BaseActivity(layout)