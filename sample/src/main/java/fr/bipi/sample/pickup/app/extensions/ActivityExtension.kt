package fr.bipi.sample.pickup.app.extensions

import android.app.Activity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

fun Activity.requireActionbar(): ActionBar? = when (this) {
    is AppCompatActivity -> this.supportActionBar
    is FragmentActivity -> null
    else -> null
}
