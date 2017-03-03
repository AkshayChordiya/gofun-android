package com.akshay.gofun.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.akshay.gofun.R
import com.akshay.gofun.ui.fragment.TimerFragment
import com.akshay.gofun.utils.AccountUtils
import com.akshay.gofun.utils.load
import kotlinx.android.synthetic.main.activity_main.*
import me.pushy.sdk.Pushy

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class MainActivity : AppCompatActivity() {

    val LOG_TAG = "MainActivity"

    val RETURN_CODE = 74

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Pushy.listen(this)
        setSupportActionBar(toolbar)
        supportFragmentManager.load { replace(R.id.base_layout, TimerFragment.newInstance()) }
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

            }
        }
        if (!AccountUtils.isLoggedIn(baseContext)) startActivityForResult(Intent(baseContext, LoginActivity::class.java), RETURN_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_about -> Log.d(LOG_TAG, "Clicked About")
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            when (requestCode) {
                RETURN_CODE -> finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
