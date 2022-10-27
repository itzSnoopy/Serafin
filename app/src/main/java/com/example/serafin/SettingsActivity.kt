package com.example.serafin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.navigation.NavigationView

class SettingsActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        /*
            Navigation Menu
        */
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayoutSettings)
        val navView : NavigationView = findViewById(R.id.nav_view_settings)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener{
            var newActivity: Intent? = null

            when(it.itemId){
                /*
                R.id.nav_home -> {
                    newActivity = Intent(this,MainActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_home, Toast.LENGTH_LONG).show()
                }
                R.id.nav_settings -> {
                    newActivity = Intent(this, SettingsActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_settings, Toast.LENGTH_LONG).show()
                }
                R.id.nav_logout -> {
                    newActivity = Intent(this,LoginActivity::class.java)
                    Toast.makeText(applicationContext, R.string.nav_logout, Toast.LENGTH_LONG).show()
                }
                 */
            }

            if(newActivity != null){
                startActivity(newActivity)
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}