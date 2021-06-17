package frame.basis.jitpack.extension.net


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import frame.basis.jitpack.extension.content.findPathByUriExpand

fun Uri.isFileExistsExpand(context: Context): Boolean {
    return runCatching {
        context.contentResolver.openAssetFileDescriptor(this, "r")?.close()
    }.isSuccess
}

fun Uri.deleteExpand(context: Context) {
    runCatching {
        context.contentResolver.delete(this, null, null)
    }
        .onSuccess { Log.i("UriUtils", "delete uri success:$this") }
        .onFailure { Log.e("UriUtils", "delete uri failure:$this") }
}

fun Uri.filePathExpand(context: Context): String {
    return when (scheme) {
        ContentResolver.SCHEME_CONTENT -> context.findPathByUriExpand(this).orEmpty()
        ContentResolver.SCHEME_FILE -> path.orEmpty()
        else -> throw RuntimeException("unsupported uri")
    }
}

fun Uri?.orEmptyExpand(): Uri = this ?: Uri.EMPTY

fun String.getQueryParameterExpand(key: String, defaultValue: String = ""): String =
    Uri.parse(this).getQueryParameter(key) ?: defaultValue

fun String.getBooleanQueryParameterExpand(key: String, defaultValue: Boolean = false): Boolean =
    Uri.parse(this).getBooleanQueryParameter(key, defaultValue)

fun String.getListQueryParameterExpand(vararg key: String): ArrayList<String> {
    val parse = Uri.parse(this)
    val list = ArrayList<String>()
    key.forEach {
        list.add(parse.getQueryParameter(it) ?: "")
    }
    return list
}