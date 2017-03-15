package com.adityakamble49.ttl.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */

/**
 * Get string [SharedPreferences]
 * @param context  context
 * @param key      of pref value
 * @param defValue of required string
 * @return string value
 */
fun getString(context: Context, key: String, defValue: String): String {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    return pref.getString(key, defValue)
}

fun putString(context: Context, key: String, value: String) {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    pref.edit{
        putString(key, value)
    }
}

fun getLong(context: Context, key: String, defValue: Long): Long {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    return pref.getLong(key, defValue)
}

fun putLong(context: Context, key: String, value: Long) {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    pref.edit{
        putLong(key, value)
    }
}

fun exists(context: Context, key: String): Boolean {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    return pref.contains(key)
}

fun remove(context: Context, key: String) {
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    pref.edit{
        remove(key)
    }
}

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}