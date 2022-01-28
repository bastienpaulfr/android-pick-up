package fr.bipi.sample.pickup.app.extensions

import android.content.Context
import android.content.res.Resources

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toResString(context: Context): String {
    return context.getString(this)
}

fun Int.toResString(context: Context, vararg arguments: Any): String {
    return context.getString(this, *arguments)
}
