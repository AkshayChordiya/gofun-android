package com.adityakamble49.ttl.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityakamble49.ttl.R
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
        Log.d(LOG_TAG, "Time = " + didTimeStart(context) + " " + getInTime(context))
        if (didTimeStart(context)) {
            time_in.text = getHourMinFormat(getInTime(context))
            time_out.text = getHourMinFormat(calculateEndTime())
            Log.d(LOG_TAG, "End = " + getRemainingTime(calculateEndTime()))
            remaining_time.text = getHourMinSecFormat(convertToMillis())
            go_home_progress.progress = 100F
            mTimer = object : CountDownTimer(convertToMillis(), 1000) {
                override fun onFinish() {
                    time_in.text = getString(R.string.default_time)
                    time_out.text = getString(R.string.default_time)
                    remaining_time.text = getString(R.string.default_remaining_time)
                    go_home_progress.progress = 0F
                }

                override fun onTick(millisUntilFinished: Long) {
                    Log.d(LOG_TAG, "Remaining = " + millisUntilFinished)
                    Log.d(LOG_TAG, "Remaining = " + getHourMinSecFormat(millisUntilFinished))
//                remaining_time.text = getHourMinSecFormat(millisUntilFinished)
                }
            }.start()
            /*LocalBroadcastManager.getInstance(context).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (mTimer != null) {
                    (mTimer as CountDownTimer).start()
                }
            }
        }, IntentFilter(PushReceiver.START_TIMER))*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mTimer?.cancel()
    }
}