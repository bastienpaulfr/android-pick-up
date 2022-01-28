package fr.bipi.sample.pickup.app.extensions

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

/*
 *   This function returns the Color Int object from a color resource. If the context is null,
 *   the default color (second argument with black as default value) is returned
 */
fun Context?.getColorCompat(resId: Int, defaultColorInt: Int = Color.BLACK): Int = this?.let {
    ContextCompat.getColor(it, resId)
} ?: defaultColorInt
