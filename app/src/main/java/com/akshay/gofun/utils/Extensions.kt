package com.akshay.gofun.utils

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
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

@ColorInt fun AppCompatActivity.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(baseContext, colorRes)
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}