package fr.bipi.sample.pickup.data.sources.settings

import android.content.SharedPreferences
import fr.bipi.sample.pickup.data.AppSettings

private const val KEY_PREFERENCE = "key_preference"

private const val PREFERENCE_DEF = 1.1

class AppSettingsSource(private val pref: SharedPreferences) : AppSettings {

    override val preference: Double
        get() = pref.getString(KEY_PREFERENCE, null)?.toDouble() ?: PREFERENCE_DEF
}
