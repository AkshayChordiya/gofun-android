package com.adityakamble49.ttl.utils

import android.os.Build

/**
 *
 * @author Akshay
 * @since 06-03-2017.
 * @version 1.0
 */
/**
 * Get if the device is running Marshmallow above

 * @return true if running
 */
fun hasMarshmallowApi(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

/**
 * Get if the device is running Lollipop above

 * @return true if running
 */
fun hasLollipopApi(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}

/**
 * Get if the device is running KitKat above

 * @return true if running
 */
fun hasKitKatApi(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
}

/**
 * Get if the device is running Jelly bean above

 * @return true if running
 */
fun hasJellyBeanApi(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
}