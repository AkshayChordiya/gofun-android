package com.akshay.gofun.utils

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
inline fun FragmentManager.load(func: FragmentTransaction.() -> Unit) {
    val transaction = beginTransaction()
    transaction.func()
    transaction.commit()
}
