package frame.basis.jitpack.extension.widget

import android.widget.SeekBar

fun SeekBar.doOnProgressChangedExpand(action: (seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit): SeekBar =
    setSeekBarChangeListenerExpand(onProgressChanged = action)

fun SeekBar.doOnStartTrackingTouchExpand(action: (seekBar: SeekBar) -> Unit): SeekBar =
    setSeekBarChangeListenerExpand(onStartTrackingTouch = action)

fun SeekBar.doOnStopTrackingTouchExpand(action: (seekBar: SeekBar) -> Unit): SeekBar =
    setSeekBarChangeListenerExpand(onStopTrackingTouch = action)

fun SeekBar.setSeekBarChangeListenerExpand(
    onProgressChanged: (seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit = { _, _, _ -> },
    onStartTrackingTouch: (seekBar: SeekBar) -> Unit = { _ -> },
    onStopTrackingTouch: (seekBar: SeekBar) -> Unit = { _ -> },
): SeekBar {
    val listener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) =
            onProgressChanged.invoke(seekBar, progress, fromUser)

        override fun onStartTrackingTouch(seekBar: SeekBar) = onStartTrackingTouch.invoke(seekBar)
        override fun onStopTrackingTouch(seekBar: SeekBar) = onStopTrackingTouch.invoke(seekBar)
    }
    setOnSeekBarChangeListener(listener)
    return this
}