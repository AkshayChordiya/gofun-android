package com.akshay.gofun.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akshay.gofun.R

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class TimerFragment : Fragment() {

    companion object {
        fun newInstance(): TimerFragment {
            return TimerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_timer, container, false)
    }
}