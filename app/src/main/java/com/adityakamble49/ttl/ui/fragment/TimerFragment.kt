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
import com.adityakamble49.ttl.utils.calculateEndTime
import com.adityakamble49.ttl.utils.getHourMinFormat
import com.adityakamble49.ttl.utils.getHourMinSecFormat
import com.adityakamble49.ttl.utils.getInTime
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class TimerFragment : Fragment() {

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
        time_in.text = getHourMinFormat(getInTime(context))
        time_out.text = getHourMinFormat(calculateEndTime())
        remaining_time.text = getHourMinSecFormat(calculateEndTime() - System.currentTimeMillis())
        mTimer = object : CountDownTimer(calculateEndTime() - System.currentTimeMillis(), 1000) {
            override fun onFinish() {
                time_in.text = getString(R.string.default_time)
                time_out.text = getString(R.string.default_time)
                remaining_time.text = getString(R.string.default_remaining_time)
                go_home_progress.progress = 0F
            }

            override fun onTick(millisUntilFinished: Long) {
                remaining_time.text = getHourMinSecFormat(millisUntilFinished)
            }
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (mTimer != null) {
                    (mTimer as CountDownTimer).start()
                }
            }
        }, IntentFilter(PushReceiver.START_TIMER))
    }


}