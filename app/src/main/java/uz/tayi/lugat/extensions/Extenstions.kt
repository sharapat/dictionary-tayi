package uz.tayi.lugat.extensions

import android.content.SharedPreferences
import android.view.View

fun View.visible(): View {
    this.visibility = View.VISIBLE
    return this
}

fun View.invisible(): View {
    this.visibility = View.INVISIBLE
    return this
}

fun SharedPreferences.language(): String? {
    return getString("language_list", null)
}

fun SharedPreferences.language(locale: String) {
    edit().putString("language_list", locale).apply()
}