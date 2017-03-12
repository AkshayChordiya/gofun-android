package com.adityakamble49.ttl.utils

import android.content.Context
import android.os.Build
import android.support.v7.app.AlertDialog
import android.webkit.WebView
import com.adityakamble49.ttl.BuildConfig
import com.adityakamble49.ttl.R

/**
 *
 * @author Akshay
 * @since 06-03-2017.
 * @version 1.0
 */

/**
 * Creates dialog to show app changelog
 *
 * @param context context
 * @return dialog to show app changelog
 */
fun createChangelogDialog(context: Context): AlertDialog {
    val webView = WebView(context)
    webView.loadUrl("file:///android_asset/changelog.html")
    return AlertDialog.Builder(context).setTitle(R.string.about_changelog)
            .setView(webView)
            .setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })
            .create()
}

/**
 * Creates dialog to show open source licenses
 *
 * @param context context
 * @return dialog to show open source licenses
 */
fun createLicensesDialog(context: Context): AlertDialog {
    val webView = WebView(context)
    webView.loadUrl("file:///android_asset/licenses.html")
    return AlertDialog.Builder(context).setTitle(R.string.about_license)
            .setView(webView)
            .setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })
            .create()
}

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

/**
 * Get the basic device details to include in Support Email
 */
fun getDeviceDetails(): String = "- - - - - - - - - - - - -" + '\n' +
        "App Version: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})\n" +
        "Android Version: ${Build.VERSION.RELEASE} (${Build.VERSION.SDK_INT})\n" +
        "Build version: ${Build.DISPLAY}\n" +
        "Brand: ${Build.BRAND}\n" +
        "Device Codename: ${Build.DEVICE}\n" +
        "Model: ${Build.MODEL}"