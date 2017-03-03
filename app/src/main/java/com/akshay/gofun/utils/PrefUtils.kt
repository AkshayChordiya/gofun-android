package com.akshay.gofun.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class PrefUtils {

    companion object {
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

        fun exists(context: Context, key: String): Boolean {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.contains(key)
        }

        inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
            val editor = edit()
            editor.func()
            editor.apply()
        }
    }

    /*inline fun SharedPreferences.getString(context: Context, key: String, defValue: String): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, defValue)
    }*/

}
