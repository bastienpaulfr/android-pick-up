package fr.bipi.sample.pickup.app.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.getBitmap(): Bitmap? {
    val bitmap =
        Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.setGone(gone: Boolean) {
    this.visibility = if (gone) View.GONE else View.VISIBLE
}

fun View.setBackgroundResourceRetainPadding(resourceID: Int) {
    val bottom = paddingBottom
    val top = paddingTop
    val right = paddingRight
    val left = paddingLeft
    setBackgroundResource(resourceID)
    setPadding(left, top, right, bottom)
}

@SuppressLint("ResourceType")
fun View.getHumanReadableId(): String {
    val out = StringBuilder()
    if (id != View.NO_ID) {
        val r = context.resources
        if (id > 0 && id.ushr(24) != 0 && r != null) {
            try {
                val pkgName: String = when (id and -0x1000000) {
                    0x7f000000 -> "app"
                    0x01000000 -> "android"
                    else -> r.getResourcePackageName(id)
                }
                val typeName = r.getResourceTypeName(id)
                val entryName = r.getResourceEntryName(id)
                out.append(" ")
                out.append(pkgName)
                out.append(":")
                out.append(typeName)
                out.append("/")
                out.append(entryName)
            } catch (e: Resources.NotFoundException) {
            }
        }
    }
    return out.toString()
}

fun View.getLayoutInflater() = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/** Milliseconds used for UI animations */
const val ANIMATION_FAST_MILLIS = 50L
const val ANIMATION_SLOW_MILLIS = 100L

/**
 * Simulate a button click, including a small delay while it is being pressed to trigger the
 * appropriate animations.
 */
fun View.simulateClick(delay: Long = ANIMATION_FAST_MILLIS) {
    performClick()
    isPressed = true
    invalidate()
    postDelayed(
        {
            invalidate()
            isPressed = false
        },
        delay
    )
}
