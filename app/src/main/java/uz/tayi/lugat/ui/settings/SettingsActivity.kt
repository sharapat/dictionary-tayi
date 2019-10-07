package uz.tayi.lugat.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import uz.tayi.lugat.R
import uz.tayi.lugat.ui.base.BaseActivity
import uz.tayi.lugat.ui.main.MainActivity

class SettingsActivity : BaseActivity() {

    companion object {
        const val RECREATE_ACTIVITY = "recreateActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.settings)

        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(RECREATE_ACTIVITY, true)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        return if (id == android.R.id.home) {
            onBackPressed()
            finish()
            true
        } else false
    }
}
