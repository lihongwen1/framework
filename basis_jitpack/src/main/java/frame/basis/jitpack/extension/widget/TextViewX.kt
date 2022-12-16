// @see androidx.core:core-ktx:version   androidx.core.widget.TextView
// TextView
package frame.basis.jitpack.extension.widget

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.doOnAfterTextChangedExpand(action: (editable: Editable) -> Unit): TextView =
    addSeekBarChangeListenerExpand(afterTextChanged = action)

fun TextView.doOnBeforeTextChangedExpand(action: (charSequence: CharSequence, start: Int, count: Int, after: Int) -> Unit): TextView =
    addSeekBarChangeListenerExpand(beforeTextChanged = action)

fun TextView.doOnTextChangedExpand(action: (charSequence: CharSequence, start: Int, before: Int, count: Int) -> Unit): TextView =
    addSeekBarChangeListenerExpand(onTextChanged = action)

fun TextView.addSeekBarChangeListenerExpand(
    afterTextChanged: (editable: Editable) -> Unit = { _: Editable -> },
    beforeTextChanged: (charSequence: CharSequence, start: Int, count: Int, after: Int) -> Unit = { _: CharSequence, _: Int, _: Int, _: Int -> },
    onTextChanged: (charSequence: CharSequence, start: Int, before: Int, count: Int) -> Unit = { _: CharSequence, _: Int, _: Int, _: Int -> },
): TextView {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) = afterTextChanged.invoke(s)
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
            beforeTextChanged.invoke(s, start, count, after)

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
            onTextChanged.invoke(s, start, before, count)
    }
    addTextChangedListener(textWatcher)
    return this
}

