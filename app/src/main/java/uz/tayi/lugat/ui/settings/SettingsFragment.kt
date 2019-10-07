package uz.tayi.lugat.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import uz.tayi.lugat.R
import uz.tayi.lugat.helper.LocaleHelper

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (context?.getString(R.string.pref_language_key) == key) {
                LocaleHelper.setLocale(context!!, sharedPreferences.getString(key, getString(R.string.pref_language_uzbek_cyr))!!)
            }
        }
    }

    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onStart() {
        super.onStart()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onStop() {
        super.onStop()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}