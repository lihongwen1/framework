package frame.basis.jitpack.extension.content


import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.net.VpnService
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import frame.basis.jitpack.extension.graphics.drawable.minimumWidthAndHeightDrawableExpand
import frame.basis.jitpack.extension.version.hasQExpand
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.Executor

val Context.dataDirCompat: File?
    get() = ContextCompat.getDataDir(this)

val Context.obbDirsCompat: Array<File>
    get() = ContextCompat.getObbDirs(this)

val Context.externalCacheDirsCompat: Array<File>
    get() = ContextCompat.getExternalCacheDirs(this)

val Context.noBackupFilesDirCompat: File?
    get() = ContextCompat.getNoBackupFilesDir(this)

val Context.codeCacheDirCompat: File
    get() = ContextCompat.getCodeCacheDir(this)

val Context.isDeviceProtectedStorageCompat: Boolean
    get() = ContextCompat.isDeviceProtectedStorage(this)

val Context.mainExecutorCompat: Executor
    get() = ContextCompat.getMainExecutor(this)

val Context.isLandscapeExpand: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Context.isFirstConnectExpand: Boolean
    get() = VpnService.prepare(this) != null

val Context.isRtlExpand: Boolean
    get() = TextUtilsCompat.getLayoutDirectionFromLocale(resources.configuration.locale) == ViewCompat.LAYOUT_DIRECTION_RTL

val Context.installTimeExpand: Long
    @SuppressLint("PackageManagerGetSignatures")
    get() = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).lastUpdateTime

val Context.installDayExpand: Int
    get() = ((System.currentTimeMillis() - installTimeExpand) / (1000 * 60 * 60 * 24)).toInt()

val Context.userIconExpand: Drawable
    get() = getAppIconExpand(packageName)

val Context.isCameraExpand: Boolean
    get() = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)

val Context.statusBarHeightExpand: Int
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    get() {
        return runCatching {
            val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
            resources.getDimensionPixelSize(resourceId)
        }.getOrNull() ?: 0
    }

fun Context.getAppIconExpand(packageName: String): Drawable =
    packageManager.getApplicationInfo(packageName, 0).loadIcon(packageManager)

fun Context.toastExpand(any: Any): Unit =
    Toast.makeText(this, any.toString(), Toast.LENGTH_SHORT).show()

fun Context.externalFilesDirsExpand(type: String?): Array<File> =
    ContextCompat.getExternalFilesDirs(this, type)

fun Context.drawableExpand(@DrawableRes id: Int): Drawable? =
    ContextCompat.getDrawable(this, id)

fun Context.colorStateListExpand(@ColorRes id: Int): ColorStateList? =
    ContextCompat.getColorStateList(this, id)

fun Context.colorExpand(@ColorRes id: Int): Int =
    ContextCompat.getColor(this, id)

fun Context.checkPermissionExpand(permission: String): Int =
    ContextCompat.checkSelfPermission(this, permission)

fun Context.booleanExpand(@BoolRes id: Int): Boolean =
    this.resources.getBoolean(id)

fun Context.intExpand(@IntegerRes id: Int): Int =
    this.resources.getInteger(id)

@RequiresApi(Build.VERSION_CODES.Q)
fun Context.floatExpand(@DimenRes id: Int): Float =
    this.resources.getFloat(id)

fun Context.textExpand(@StringRes id: Int): CharSequence =
    this.resources.getText(id)

fun Context.stringExpand(@StringRes id: Int): String =
    this.resources.getString(id)

fun Context.stringExpand(@StringRes id: Int, vararg formatArgs: Any): String =
    this.resources.getString(id, *formatArgs)

fun Context.textArrayExpand(@ArrayRes id: Int): Array<CharSequence> =
    this.resources.getTextArray(id)

fun Context.stringArrayExpand(@ArrayRes id: Int): Array<String> =
    this.resources.getStringArray(id)

fun Context.intArrayExpand(@ArrayRes id: Int): IntArray =
    this.resources.getIntArray(id)

fun Context.dimensionExpand(@DimenRes id: Int): Float =
    this.resources.getDimension(id)

fun Context.dimensionPixelOffsetExpand(@DimenRes id: Int): Int =
    this.resources.getDimensionPixelOffset(id)

fun Context.dimensionPixelSizeExpand(@DimenRes id: Int): Int =
    this.resources.getDimensionPixelSize(id)

fun Context.quantityTextExpand(@PluralsRes id: Int, quantity: Int): CharSequence =
    this.resources.getQuantityText(id, quantity)

fun Context.quantityStringExpand(
    @PluralsRes id: Int,
    quantity: Int,
    vararg formatArgs: Any,
): CharSequence = this.resources.getQuantityString(id, quantity, *formatArgs)

fun Context.quantityStringExpand(@PluralsRes id: Int, quantity: Int): String =
    this.resources.getQuantityString(id, quantity)

@RequiresApi(Build.VERSION_CODES.O)
fun Context.fontExpand(@FontRes id: Int): Typeface =
    this.resources.getFont(id)

fun Context.appIntentExpand(packageName: String): Intent? =
    packageManager.getLaunchIntentForPackage(packageName)

fun Context.minimumDrawableExpand(@DrawableRes id: Int, @ColorInt color: Int): Drawable? =
    drawableExpand(id)?.minimumWidthAndHeightDrawableExpand(color)

fun Context.moveToNextToIdExpand(uri: Uri): Boolean =
    contentResolver.moveToNextToIdExpand(uri)

fun Context.moveToNextExpand(uri: Uri, name: String): Boolean =
    contentResolver.moveToNextExpand(uri, name)

fun Context.findIdByUriExpand(uri: Uri): Long =
    contentResolver.queryIdExpand(uri)

fun Context.findPathByUriExpand(uri: Uri): String? =
    contentResolver.queryDataExpand(uri)

fun Context.pendingIntentExpand(clazz: Class<*>): PendingIntent? {
    val intent = Intent(this, clazz)
    val flags = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        else -> PendingIntent.FLAG_UPDATE_CURRENT
    }
    return PendingIntent.getBroadcast(this, 1, intent, flags)
}

fun Context.getJsonExpand(fileName: String): String {
    val stringBuilder = StringBuilder()
    BufferedReader(InputStreamReader(assets.open(fileName))).use { it ->
        it.lineSequence().forEach { stringBuilder.append(it) }
    }
    return stringBuilder.toString()
}

fun Context.shareTextPlainExpand(title: String, message: String?) {
    val textIntent = Intent(Intent.ACTION_SEND)
    textIntent.type = "text/plain"
    textIntent.putExtra(Intent.EXTRA_TEXT, message)
    startActivity(Intent.createChooser(textIntent, title))
}

fun Context.insertImageUriExpand(contentValues: ContentValues): Uri? =
    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    } else {
        null
    }

fun Context.insertImageUriExpand(
    file: File,
    relativePath: String = Environment.DIRECTORY_DCIM,
): Uri? = insertImageUriExpand(ContentValues().apply {
    if (hasQExpand()) {
        put(MediaStore.MediaColumns.DISPLAY_NAME, file.name)
        put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
    } else {
        put(MediaStore.MediaColumns.DATA, file.path)
    }
})

fun Context.insertVideoUriExpand(contentValues: ContentValues): Uri? =
    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
        contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
    } else {
        null
    }

fun Context.insertVideoUriExpand(
    file: File,
    relativePath: String = Environment.DIRECTORY_DCIM,
): Uri? = insertVideoUriExpand(ContentValues().apply {
    if (Build.VERSION.SDK_INT >= 29) {
        put(MediaStore.MediaColumns.DISPLAY_NAME, file.name)
        put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath)
    } else {
        put(MediaStore.MediaColumns.DATA, file.path)
    }
})

fun Context.openVideoExpand(uri: Uri, error: () -> Unit) {
    runCatching {
        val video = Intent(Intent.ACTION_VIEW)
        video.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        video.setDataAndType(uri, "video/*")
        startActivity(video)
    }.onFailure { error.invoke() }
}

fun Context.openAppStoreExpand(packageName: String, error: () -> Unit) {
    runCatching {
        val market = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        market.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(market)
    }.onFailure { error.invoke() }
}