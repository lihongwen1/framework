package frame.basis.jitpack.extension.util

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import frame.basis.jitpack.extension.content.insertImageUriExpand
import frame.basis.jitpack.extension.content.insertVideoUriExpand
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun String.mkdirsFileExpand(child: String): File {
    val pathFile = File(this, child)
    if (!pathFile.exists()) {
        pathFile.mkdirs()
    }
    return pathFile
}

fun Context.lowerVersionFileExpand(
    fileName: String,
    relativePath: String = Environment.DIRECTORY_DCIM
): File = File(
    if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
        Environment.getExternalStoragePublicDirectory(relativePath).path
    } else {
        externalCacheDir?.path ?: cacheDir.path
    }, fileName
)

fun Context.copyImageExpand(
    inputUri: Uri,
    displayName: String,
    relativePath: String = Environment.DIRECTORY_DCIM
): Uri? {
    if (Build.VERSION.SDK_INT < 29) {
        return null
    }
    val contentValues = ContentValues()
    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
    val outPutUri: Uri? = insertImageUriExpand(contentValues)
    outPutUri ?: return null
    return copyFileExpand(inputUri, outPutUri)
}

fun Context.copyVideoExpand(
    inputUri: Uri,
    displayName: String,
    relativePath: String = Environment.DIRECTORY_DCIM
): Uri? {
    if (Build.VERSION.SDK_INT < 29) {
        return null
    }
    val contentValues = ContentValues()
    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
    val outPutUri: Uri? = insertVideoUriExpand(contentValues)
    outPutUri ?: return null
    return copyFileExpand(inputUri, outPutUri)
}

fun Context.copyFileExpand(inputUri: Uri, outPutUri: Uri): Uri? {
    val outStream: OutputStream = contentResolver.openOutputStream(outPutUri) ?: return null
    val inStream: InputStream = contentResolver.openInputStream(inputUri) ?: return null
    outStream.use { out -> inStream.use { input -> input.copyTo(out) } }
    return outPutUri
}