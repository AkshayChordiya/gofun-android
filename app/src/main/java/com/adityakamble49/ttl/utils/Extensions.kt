package com.adityakamble49.ttl.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.adityakamble49.ttl.BuildConfig
import com.adityakamble49.ttl.R
import java.io.BufferedReader
import java.io.File

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

fun AppCompatActivity.sendSupportEmail(email: String = "akshaychordiya2@gmail.com") {
    // save logcat in file
    val log = Runtime.getRuntime().exec("logcat -d").inputStream.bufferedReader().use { BufferedReader::readText }
    val outputFile = File(Environment.getExternalStorageDirectory().toString() + "/" +
            "logcat.txt")
    outputFile.writeText(log.toString())

    /* Create the Email Intent */
    val emailIntent = Intent(Intent.ACTION_SEND)
    /* Fill it with Data */
    emailIntent.type = "text/plain"
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback " + getString(R.string.app_name)
            + " v" + BuildConfig.VERSION_NAME)
    emailIntent.putExtra(Intent.EXTRA_TEXT,
            "\n\n" + getDeviceDetails())
    if (outputFile.exists()) {
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(outputFile))
    }

    /* Send it off to the Activity-Chooser */
    startActivity(Intent.createChooser(emailIntent, getString(R.string.about_email)))
}