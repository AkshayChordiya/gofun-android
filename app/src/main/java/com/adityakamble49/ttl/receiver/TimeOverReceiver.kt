package com.adityakamble49.ttl.receiver

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.NotificationCompat
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.ui.activity.MainActivity
import com.adityakamble49.ttl.utils.disableComponent
import com.adityakamble49.ttl.utils.removeInTime

/**
 *
 * @author Akshay
 * @since 06-03-2017.
 * @version 1.0
 */
class TimeOverReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        // Delete time from storage
        removeInTime(context)
        // Show Notification
        val notification = with(NotificationCompat.Builder(context)) {
            setSmallIcon(R.drawable.ic_timer)
            setContentTitle(context.getString(R.string.app_name))
            setContentText(context.getString(R.string.time_over))
            setContentIntent(PendingIntent.getActivity(context, 14, Intent(context, MainActivity::class.java), 0))
            setDefaults(Notification.DEFAULT_ALL)
            setAutoCancel(true)
            setLights(ContextCompat.getColor(context, R.color.colorAccent), 1000, 1000)
            build()
        }
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(74, notification)
        // TODO: Send an local broadcast to UI to stop time
        context.packageManager.disableComponent<RestartTimeBootReceiver>(context)
    }

}
