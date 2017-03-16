package com.adityakamble49.ttl.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 * @author Akshay Chordiya
 * @since 16-03-2017.
 * @version 1.0
 */
class DateUtilsTest {

    @Test
    fun toHourMinFormatTest() {
        val time = parseDate("HH:mm", 1489645565079)
        assertEquals("11:56", time)
    }

    @Test
    fun parseDateTest() {
        val date = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(1489645565079))
        assertEquals("11:56", date)
    }

    @Test
    fun convertToMillisTest() {
        val millis = ((7 * 60 * 60000) + (30 * 60000)).toLong()
        assertEquals(27000000, millis)
    }

    @Test
    fun getPercentLeftTest() {
        val timeLeftInMillis = 27000000L
        assertEquals(100.0F, timeLeftInMillis.toFloat() / convertToMillis().toFloat() * 100)
    }

    @Test
    fun toReadableTimeTest() {
        val millis = 27000000L
        val format = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit
                        .MILLISECONDS.toHours(millis)), TimeUnit
                .MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit
                .MILLISECONDS.toMinutes(millis)))
        assertEquals("07:30:00", format)
    }
}