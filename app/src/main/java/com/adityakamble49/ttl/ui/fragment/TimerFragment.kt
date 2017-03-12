package com.adityakamble49.ttl.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.receiver.PushReceiver
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
        if (didTimeStart(context)) {
            time_in.text = toHourMinFormat(getInTime(context))
            time_out.text = toHourMinFormat(getEndTime(context))
            remaining_time.text = toReadableTime(getRemainingTime(context))
            go_home_progress.progress = 100F
            mTimer = object : CountDownTimer(getRemainingTime(context), 1000) {
                override fun onFinish() {
                    time_in.text = getString(R.string.default_time)
                    time_out.text = getString(R.string.default_time)
                    remaining_time.text = getString(R.string.default_remaining_time)
                    go_home_progress.progress = 0F
                }

                override fun onTick(millisUntilFinished: Long) {
                    remaining_time.text = toReadableTime(millisUntilFinished)
                    go_home_progress.setProgressWithAnimation(getPercentLeft(millisUntilFinished))
                }
            }.start()
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                mTimer?.apply { start() }
            }
        }, IntentFilter(PushReceiver.START_TIMER))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mTimer?.cancel()
    }
}