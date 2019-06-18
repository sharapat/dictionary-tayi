package uz.tayi.lugat.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context: Context) {

    companion object {
        const val IS_FIRST_LAUNCH = "is_first_launch"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun isFirstLaunch() : Boolean =
            preferences.getBoolean(IS_FIRST_LAUNCH, true)

    fun setFirstLaunch() {
        preferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply()
    }
}