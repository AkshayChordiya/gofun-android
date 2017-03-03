package com.akshay.gofun.utils

import android.content.Context

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class AccountUtils {

    companion object {

        val PREF_TOKEN_KEY = "token"

        fun getToken(context: Context): String {
            return PrefUtils.getString(context, PREF_TOKEN_KEY, "")
        }

        fun isLoggedIn(context: Context): Boolean {
            when {
                PrefUtils.exists(context, PREF_TOKEN_KEY) -> return true
                else -> return getToken(context).isNotEmpty()
            }
        }

        fun setToken(context: Context, token: String) {
            PrefUtils.putString(context, PREF_TOKEN_KEY, token)
        }

    }
}