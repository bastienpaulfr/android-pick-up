package fr.bipi.sample.pickup.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import fr.bipi.sample.pickup.BuildConfig.DEBUG
import fr.bipi.sample.pickup.R
import fr.bipi.sample.pickup.data.AppSettings
import fr.bipi.sample.pickup.data.KEY_CLEAR_LOG
import fr.bipi.sample.pickup.data.KEY_EXPORT_LOG
import fr.bipi.sample.pickup.data.KEY_VERBOSE_DEBUG
import fr.bipi.sample.pickup.data.KEY_VERSION
import fr.bipi.sample.pickup.databinding.SettingsActivityBinding
import fr.bipi.sample.pickup.ui.common.components.Toaster
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity :
    AppCompatActivity(),
    androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    companion object {
        fun show(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }

        private const val TITLE_TAG = "settingsActivityTitle"
    }

    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, HeaderFragment())
                .commit()
        } else {
            title = savedInstanceState.getCharSequence(TITLE_TAG)
        }
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                setTitle(R.string.title_activity_settings)
            }
        }
        configureToolBar()
    }

    private fun configureToolBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, title)
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (supportFragmentManager.popBackStackImmediate()) {
            true
        } else if (!super.onSupportNavigateUp()) {
            finish()
            true
        } else {
            true
        }
    }

    override fun onPreferenceStartFragment(
        caller: androidx.preference.PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment
        ).apply {
            arguments = args
            setTargetFragment(caller, 0)
        }
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings, fragment)
            .addToBackStack(null)
            .commit()
        title = pref.title
        return true
    }

    class HeaderFragment : com.takisoft.preferencex.PreferenceFragmentCompat() {
        private val settings: AppSettings by inject()

        override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey)

            findPreference<Preference>(KEY_VERSION)?.summary = settings.version
        }
    }

    @Suppress("unused")
    class AppFragment : com.takisoft.preferencex.PreferenceFragmentCompat() {

        private val appSettingsViewModel: AppSettingsViewModel by viewModel()
        private val toaster: Toaster by inject()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            appSettingsViewModel.exportState.observe(this.viewLifecycleOwner) { state ->
                when (state) {
                    AppSettingsViewModel.ExportLogState.Done -> toaster.toast(R.string.msg_logs_exported)
                    AppSettingsViewModel.ExportLogState.Running -> {
                    }
                }
            }
        }

        override fun onCreatePreferencesFix(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.app_preferences, rootKey)

            findPreference<Preference>(KEY_EXPORT_LOG)?.setOnPreferenceClickListener {
                appSettingsViewModel.exportLogs()
                true
            }

            findPreference<Preference>(KEY_CLEAR_LOG)?.apply {
                if (DEBUG) {
                    setOnPreferenceClickListener {
                        appSettingsViewModel.clearLogs()
                        toaster.toast(R.string.msg_logs_cleared)
                        true
                    }
                } else {
                    this.isVisible = false
                }
            }

            findPreference<Preference>(KEY_VERBOSE_DEBUG)?.apply {
                if (DEBUG) {
                    setOnPreferenceClickListener {
                        toaster.toast(R.string.verbose_debug_changed)
                        true
                    }
                } else {
                    this.isVisible = false
                }
            }
        }
    }
}
