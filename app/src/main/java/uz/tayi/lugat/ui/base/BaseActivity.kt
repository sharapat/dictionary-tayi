package uz.tayi.lugat.ui.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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
}