package com.adityakamble49.ttl.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.adityakamble49.ttl.utils.didTimeStart
import com.adityakamble49.ttl.utils.setTimeUpAlarm

/**
 *
 * @author Akshay
 * @since 12-03-2017.
 * @version 1.0
 */
class RestartTimeBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (didTimeStart(context)) {
            // Restart countdown via Alarm
            setTimeUpAlarm(context)
        }
    }

}