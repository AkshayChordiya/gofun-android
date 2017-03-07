package com.adityakamble49.ttl.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author Akshay Chordiya
 * @since 05-03-2017.
 * @version 1.0
 */

fun getHourMinFormat(millis: Long): String {
    return parseDate("HH:mm", millis)
}

fun getHourMinSecFormat(millis: Long): String {
    return parseDate("HH:mm:ss", millis)
}

fun parseDate(pattern: String, millis: Long): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(millis))
}

fun convertToMillis(hour: Int = 7, min: Int = 30): Long {
    return ((hour * 60 * 60000) + (min * 60000)).toLong()
}

fun getRemainingTime(millis: Long): String {
    return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit
                    .MILLISECONDS.toHours(millis)), TimeUnit
            .MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit
            .MILLISECONDS.toMinutes(millis)))
}

fun calculateEndTime(hour: Int = 7, min: Int = 30): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.HOUR, hour)
    calendar.add(Calendar.MINUTE, min)
    return calendar.timeInMillis
}
