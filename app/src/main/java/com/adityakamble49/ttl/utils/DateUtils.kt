package com.adityakamble49.ttl.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author Akshay Chordiya
 * @since 05-03-2017.
 * @version 1.0
 */

fun getHourMinFormat(millis: Long): String {
    return parseDate("hh:mm", millis)
}

fun getHourMinSecFormat(millis: Long): String {
    return parseDate("hh:mm:ss", millis)
}

fun parseDate(pattern: String, millis: Long): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(millis))
}

fun calculateEndTime(hour: Int = 7, min: Int = 30): Long {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.HOUR, hour)
    calendar.add(Calendar.MINUTE, min)
    return calendar.timeInMillis
}
