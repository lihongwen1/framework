//@see androidx.core.graphics.drawable.DrawableCompat
//Drawable Expand
package frame.basis.jitpack.extension.graphics.drawable

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat

val Drawable.alphaExpand: Int
    get() = DrawableCompat.getAlpha(this)

val Drawable.canApplyThemeExpand: Boolean
    get() = DrawableCompat.canApplyTheme(this)

val Drawable.colorFilterExpand: ColorFilter?
    get() = DrawableCompat.getColorFilter(this)

val Drawable.layoutDirectionExpand: Int
    get() = DrawableCompat.getLayoutDirection(this)

fun Drawable.setHotspotExpand(x: Float, y: Float) =
    DrawableCompat.setHotspot(this, x, y)

fun Drawable.setHotspotBoundsExpand(left: Int, top: Int, right: Int, bottom: Int) =
    DrawableCompat.setHotspotBounds(this, left, top, right, bottom)

fun Drawable.setTintExpand(@ColorInt tint: Int) =
    DrawableCompat.setTint(this, tint)

fun Drawable.setTintListExpand(tint: ColorStateList?) =
    DrawableCompat.setTintList(this, tint)

fun Drawable.setTintModeExpand(tint: PorterDuff.Mode) =
    DrawableCompat.setTintMode(this, tint)

fun Drawable.applyThemeExpand(theme: Resources.Theme) =
    DrawableCompat.applyTheme(this, theme)

fun Drawable.cleanColorFilterExpand() =
    DrawableCompat.clearColorFilter(this)

fun Drawable.wrapExpand(): Drawable =
    DrawableCompat.wrap(this)

fun <T : Drawable> Drawable.unwrapExpand(): T =
    DrawableCompat.unwrap(this)

fun Drawable.setLayoutDirectionExpand(layoutDirection: Int) =
    DrawableCompat.setLayoutDirection(this, layoutDirection)

fun Drawable.minimumWidthAndHeightDrawableExpand(@ColorInt color: Int): Drawable {
    this.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    this.setBounds(0, 0, this.minimumWidth, this.minimumHeight)
    return this
}

fun GradientDrawable.cornerRadiusExpand(radius: Float) = also { cornerRadius = radius }

fun GradientDrawable.colorExpand(color: Int) = also { setColor(color) }

fun StateListDrawable.addEnabledStateExpand(radius: Float, color: Int) = also {
    addState(
        intArrayOf(android.R.attr.state_enabled),
        GradientDrawable().cornerRadiusExpand(radius).colorExpand(color)
    )
}

fun StateListDrawable.addNormalStateExpand(radius: Float, color: Int) = also {
    addState(
        intArrayOf(-android.R.attr.state_enabled),
        GradientDrawable().cornerRadiusExpand(radius).colorExpand(color)
    )
}
