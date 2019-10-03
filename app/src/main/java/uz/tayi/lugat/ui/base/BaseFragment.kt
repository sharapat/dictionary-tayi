package uz.tayi.lugat.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun toast(messageResId: Int) {
        Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
    }
}