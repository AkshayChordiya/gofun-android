package com.adityakamble49.ttl.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author Akshay Chordiya
 * @since 05-03-2017.
 * @version 1.0
 */

fun toHourMinFormat(millis: Long): String {
    return parseDate("HH:mm", millis)
}

fun parseDate(pattern: String, millis: Long): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(millis))
}

fun convertToMillis(hour: Int = 7, min: Int = 30): Long {
    return ((hour * 60 * 60000) + (min * 60000)).toLong()
}

fun getPercentLeft(timeLeftInMillis: Long): Float {
    // TODO: Get the hour and min value from Pref
    return timeLeftInMillis.toFloat() / convertToMillis().toFloat() * 100
}

fun getRemainingTime(context: Context): Long {
    // TODO: Get the hour and min value from
    return convertToMillis() - (System.currentTimeMillis() - getInTime(context))
}

fun getEndTime(context: Context): Long {
    return getInTime(context) + convertToMillis()
}

fun toReadableTime(millis: Long): String {
    return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit
                    .MILLISECONDS.toHours(millis)), TimeUnit
            .MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit
            .MILLISECONDS.toMinutes(millis)))
}