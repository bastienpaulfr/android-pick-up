package fr.bipi.sample.pickup.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import fr.bipi.sample.pickup.R
import fr.bipi.sample.pickup.databinding.ActivityMainBinding
import fr.bipi.sample.pickup.ui.settings.SettingsActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            SettingsActivity.show(this)
            true
        } else super.onOptionsItemSelected(item)
    }
}
