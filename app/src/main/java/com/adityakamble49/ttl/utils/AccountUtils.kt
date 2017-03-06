package com.adityakamble49.ttl.utils

import android.content.Context

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
val PREF_TOKEN_KEY = "token"
val PREF_TIME_KEY = "in_time"

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

fun didTimeStart(context: Context): Boolean {
    when {
        PrefUtils.exists(context, PREF_TIME_KEY) -> return true
        else -> return getInTime(context) != 0L
    }
}

fun removeInTime(context: Context) {
    PrefUtils.remove(context, PREF_TIME_KEY)
}

fun getInTime(context: Context): Long {
    return PrefUtils.getLong(context, PREF_TIME_KEY, 0)
}

fun setInTime(context: Context, time: Long) {
    PrefUtils.putLong(context, PREF_TIME_KEY, time)
}
