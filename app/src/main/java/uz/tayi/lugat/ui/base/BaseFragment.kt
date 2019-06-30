package uz.tayi.lugat.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun toast(messageResId: Int) {
        Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
    }
}