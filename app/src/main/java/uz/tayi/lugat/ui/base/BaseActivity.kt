package uz.tayi.lugat.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.tayi.lugat.helper.LocaleHelper
import uz.tayi.lugat.ui.settings.SettingsActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun toast(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    fun toast(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

    fun changeFragment(container: Int, fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(container, fragment, tag).commit()
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase != null) {
            super.attachBaseContext(LocaleHelper.onAttach(newBase))
        } else {
            super.attachBaseContext(newBase)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.getBooleanExtra(SettingsActivity.RECREATE_ACTIVITY, false)) {
            this.recreate()
        }
    }
}