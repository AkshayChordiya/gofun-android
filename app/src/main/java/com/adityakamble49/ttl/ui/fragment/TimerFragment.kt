package com.adityakamble49.ttl.ui.fragment

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.*
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.receiver.RestartTimeBootReceiver
import com.adityakamble49.ttl.receiver.TimeInReceiver
import com.adityakamble49.ttl.receiver.TimeOverReceiver
import com.adityakamble49.ttl.utils.*
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class TimerFragment : Fragment() {

    val LOG_TAG = "TimerFragment"
    var mTimer: CountDownTimer? = null

    companion object {
        fun newInstance(): TimerFragment {
            return TimerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        if (didTimeStart(context)) {
            val remainingTime = getRemainingTime(context)
            if (remainingTime > 0) {
                time_in.text = toHourMinFormat(getInTime(context))
                time_out.text = toHourMinFormat(getEndTime(context))
                remaining_time.text = toReadableTime(remainingTime)
                go_home_progress.progress = 100F
                mTimer = object : CountDownTimer(getRemainingTime(context), 1000) {
                    override fun onFinish() {
                        time_in.text = getString(R.string.default_time)
                        time_out.text = getString(R.string.default_time)
                        remaining_time.text = getString(R.string.default_remaining_time)
                        go_home_progress.progress = 0F
                        stopTimer()
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        remaining_time.text = toReadableTime(millisUntilFinished)
                        go_home_progress.setProgressWithAnimation(getPercentLeft(millisUntilFinished))
                    }
                }.start()
            } else {
                time_in.text = getString(R.string.default_time)
                time_out.text = getString(R.string.default_time)
                remaining_time.text = getString(R.string.default_remaining_time)
                go_home_progress.progress = 0F
            }
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                mTimer?.apply { start() }
            }
        }, IntentFilter(TimeInReceiver.START_TIMER))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_timer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_stop_timer -> consume {
            stopTimer()
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun stopTimer() {
        Log.d(LOG_TAG, "Timer stopped")
        removeInTime(context)
        context.getAlarmManager().cancel(PendingIntent.getBroadcast(context, 15, Intent(context, TimeOverReceiver::class.java), 0))
        context.packageManager.disableComponent<RestartTimeBootReceiver>(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mTimer?.cancel()
    }
}