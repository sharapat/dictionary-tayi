package uz.tayi.lugat.helper

import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.preference.PreferenceManager
import uz.tayi.lugat.R
import java.util.*


object LocaleHelper {

    fun onAttach(context: Context): Context {
        return setLocale(context, getLanguage(context))
    }

    fun getLanguage(context: Context): String {
        return getPersistedData(context, context.getString(R.string.pref_language_default))
    }

    fun setLocale(context: Context, language: String): Context {
        persist(context, language)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)

    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(context.getString(R.string.pref_language_key), defaultLanguage)!!
    }

    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()

        editor.putString(context.getString(R.string.pref_language_key), language)
        editor.apply()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}