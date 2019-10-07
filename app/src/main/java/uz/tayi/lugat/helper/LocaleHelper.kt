@file:Suppress("DEPRECATION")

package uz.tayi.lugat.helper

import android.os.Build
import android.annotation.TargetApi
import android.content.Context
import android.preference.PreferenceManager
import java.util.*
import android.util.Log
import android.content.ContextWrapper
import android.os.LocaleList
import uz.tayi.lugat.R
import uz.tayi.lugat.extensions.language

object LocaleHelper {

    private val SELECTED_LANGUAGE = "language_list"

    fun setLocale(context: Context, language: String): Context {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language)
        }

        return updateResourcesLegacy(context, language)
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.language() ?: defaultLanguage
    }


    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.setLayoutDirection(locale)
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }

    fun getLanguageType(context: Context): Locale? {
        Log.i("=======", "context = $context")
        return Locale(getPersistedData(context, context.resources.getString(R.string.pref_language_default)))

    }

    fun wrap(c: Context, newLocale: Locale?): ContextWrapper {
        var context = c
        val res = context.resources
        val configuration = res.configuration

        context = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(newLocale)

            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)

            context.createConfigurationContext(configuration)

        } else {
            configuration.setLocale(newLocale)
            context.createConfigurationContext(configuration)

        }

        return ContextWrapper(context)
    }
}