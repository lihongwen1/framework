package frame.basis.jitpack.extension.view

import android.text.InputFilter
import android.widget.EditText
import java.util.regex.Pattern

fun EditText.noEmojiInputExpand() {
    val pattern =
        Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_,.?!:;…~_\\-\"\"/@*+'()<>{}/[/]()<>{}\\[\\]=%&$|/♀♂#¥£¢€\"^` ，。？！：；……～“”、“（）”、（——）‘’＠‘·’＆＊＃《》￥《〈〉》〈＄〉［］￡［］｛｝｛｝￠【】【】％〖〗〖〗／〔〕〔〕＼『』『』＾「」「」｜﹁﹂｀．]")
    addInputFilterExpand(InputFilter { source, _, _, _, _, _ ->
        return@InputFilter if (!pattern.matcher(source).find()) null else ""
    })
}

fun EditText.addInputFilterExpand(inputFilter: InputFilter) {
    filters = filters.plus(inputFilter)
}