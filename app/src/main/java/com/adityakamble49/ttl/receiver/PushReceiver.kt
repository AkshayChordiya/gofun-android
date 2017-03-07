package com.adityakamble49.ttl.receiver

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.NotificationCompat
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.ui.activity.MainActivity
import com.adityakamble49.ttl.utils.*

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class PushReceiver : BroadcastReceiver() {

    companion object {
        val START_TIMER = "com.adityakamble49.ttl.start_timer"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val didTimeStart = didTimeStart(context)
        if (didTimeStart) {
            return
        }
        intent?.getStringExtra(PREF_TIME_KEY)?.let {
            with(intent) {
                // Store the in time
                val time = getStringExtra(PREF_TIME_KEY).toLong()
//                AccountUtils.setInTime(context, time)

                // Show Notification
                val notification = with(NotificationCompat.Builder(context)) {
                    setSmallIcon(R.drawable.ic_timer)
                    setContentTitle(context.getString(R.string.app_name))
                    setContentText(context.getString(R.string.notification_in_time))
                    setContentIntent(PendingIntent.getActivity(context, 14, Intent(context, MainActivity::class.java), 0))
                    setWhen(time)
                    setDefaults(Notification.DEFAULT_ALL)
                    setLights(ContextCompat.getColor(context, R.color.colorAccent), 1000, 1000)
                    build()
                }
                val notificationManager = NotificationManagerCompat.from(context)
                notificationManager.notify(74, notification)

                // Notify Local
                val mainIntent = Intent(START_TIMER)
                LocalBroadcastManager.getInstance(context).sendBroadcast(mainIntent)

                // Start countdown via Alarm
                val pendingIntent = PendingIntent.getBroadcast(context, 15, Intent(context, TimeOverReceiver::class.java), 0)
                val alarmManager = context.getAlarmManager()
                alarmManager.setExactCompat(AlarmManager.RTC, calculateEndTime(), pendingIntent)
            }
        }
    }
}