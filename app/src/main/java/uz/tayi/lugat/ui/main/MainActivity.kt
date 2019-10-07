package uz.tayi.lugat.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import uz.tayi.lugat.R
import uz.tayi.lugat.ui.about.AboutActivity
import uz.tayi.lugat.ui.base.BaseActivity
import uz.tayi.lugat.ui.dictionary.DictionaryFragment
import uz.tayi.lugat.ui.favorite.FavoriteFragment
import uz.tayi.lugat.ui.history.HistoryFragment
import uz.tayi.lugat.ui.settings.SettingsActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        changeFragment(R.id.container, DictionaryFragment(), DictionaryFragment::class.java.name)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment: Fragment = DictionaryFragment()
        when (item.itemId) {
            R.id.nav_dictionary -> {
                fragment = DictionaryFragment()
            }
            R.id.nav_favorite -> {
                fragment = FavoriteFragment()
            }
            R.id.nav_history -> {
                fragment = HistoryFragment()
            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        changeFragment(R.id.container, fragment, fragment::class.java.name)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.getBooleanExtra(SettingsActivity.RECREATE_ACTIVITY, false)) {
            this.recreate()
        }
    }

}
