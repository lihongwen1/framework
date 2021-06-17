package frame.basis.jitpack.extension.content

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

fun ContentResolver.queryExpand(uri: Uri, vararg name: String): Cursor? =
    query(uri, name, null, null, null)

fun ContentResolver.moveToNextToIdExpand(uri: Uri): Boolean =
    moveToNextExpand(uri, MediaStore.Files.FileColumns._ID)

fun ContentResolver.moveToNextExpand(uri: Uri, name: String): Boolean =
    queryExpand(uri, name).use { it?.moveToNext() ?: false }

fun ContentResolver.queryDataExpand(uri: Uri): String? =
    queryExpand(uri, MediaStore.MediaColumns.DATA).use {
        val cursor = it ?: return null
        while (cursor.moveToNext()) {
            return cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
        }
        return null
    }

fun ContentResolver.queryIdExpand(uri: Uri): Long {
    val split = uri.toString().split("/")
    var id = -1L
    runCatching {
        id = split[split.size - 1].toLong()
    }.onFailure {
        queryExpand(uri, MediaStore.Files.FileColumns._ID).use {
            val cursor = it ?: return@use
            while (cursor.moveToNext()) {
                id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
            }
        }
    }
    return id
}