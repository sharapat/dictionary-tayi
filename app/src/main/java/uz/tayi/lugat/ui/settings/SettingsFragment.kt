package uz.tayi.lugat.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import uz.tayi.lugat.R
import uz.tayi.lugat.helper.LocaleHelper

class SettingsFragment: PreferenceFragment() {
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (getString(R.string.pref_language_key) == key) {
                LocaleHelper.setLocale(activity, sharedPreferences.getString(key, getString(R.string.pref_language_uzbek_cyr))!!)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onStop() {
        super.onStop()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}