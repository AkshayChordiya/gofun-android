package com.akshay.gofun.utils

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

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}