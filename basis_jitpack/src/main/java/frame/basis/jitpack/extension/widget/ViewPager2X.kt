package frame.basis.jitpack.extension.widget

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.doOnPageScrolledExpand(action: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit): ViewPager2 =
    addOnPageChangeListenerExpand(onPageScrolled = action)

fun ViewPager2.doOnPageSelectedExpand(action: (position: Int) -> Unit): ViewPager2 =
    addOnPageChangeListenerExpand(onPageSelected = action)

fun ViewPager2.doOnPageScrollStateChangedExpand(action: (state: Int) -> Unit): ViewPager2 =
    addOnPageChangeListenerExpand(onPageScrollStateChanged = action)

fun ViewPager2.addOnPageChangeListenerExpand(
    onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { _: Int, _: Float, _: Int -> },
    onPageSelected: (position: Int) -> Unit = { _: Int -> },
    onPageScrollStateChanged: (state: Int) -> Unit = { _: Int -> }
): ViewPager2 {
    val listener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ): Unit = onPageScrolled(position, positionOffset, positionOffsetPixels)

        override fun onPageSelected(position: Int): Unit = onPageSelected(position)
        override fun onPageScrollStateChanged(state: Int): Unit = onPageScrollStateChanged(state)
    }
    registerOnPageChangeCallback(listener)
    return this
}