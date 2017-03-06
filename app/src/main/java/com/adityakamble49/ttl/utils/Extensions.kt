package com.adityakamble49.ttl.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.text.Editable

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
inline fun FragmentManager.load(func: FragmentTransaction.() -> Unit) {
    val transaction = beginTransaction()
    transaction.func()
    transaction.commit()
}

fun Editable.isEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Editable.isNotEmail(): Boolean {
    return !android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun AppCompatActivity.openUrl(url: String): Unit {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun Context.formatString(stringRes: Int, vararg args: Any?): String {
    return String.format(getString(stringRes), args)
}

fun Context.getAlarmManager(): AlarmManager {
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}

@SuppressLint("NewApi")
fun AlarmManager.setExactCompat(type: Int, triggerAtMillis: Long, operation: PendingIntent) = if (hasKitKatApi()) {
    setExact(type, triggerAtMillis, operation)
} else {
    set(type, triggerAtMillis, operation)
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}