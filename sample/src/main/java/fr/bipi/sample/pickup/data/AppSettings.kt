package fr.bipi.sample.pickup.data

import fr.bipi.sample.pickup.BuildConfig

const val KEY_VERSION = "key_version"
const val KEY_VERBOSE_DEBUG = "key_verbose_debug"
const val KEY_EXPORT_LOG = "key_export_log"
const val KEY_CLEAR_LOG = "key_clear_log"

interface AppSettings {

    val version: String
        get() = "${BuildConfig.VERSION_NAME} ${if (BuildConfig.DEBUG) "debug" else ""}"

    val preference: Double
}
