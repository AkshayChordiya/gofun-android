package com.akshay.gofun.utils

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
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

inline fun Editable.isEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

inline fun Editable.isNotEmail(): Boolean {
    return !android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}