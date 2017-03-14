package com.adityakamble49.ttl.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.ui.fragment.TimerFragment
import com.adityakamble49.ttl.utils.consume
import com.adityakamble49.ttl.utils.isLoggedIn
import com.adityakamble49.ttl.utils.load
import kotlinx.android.synthetic.main.activity_main.*
import me.pushy.sdk.Pushy

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class MainActivity : AppCompatActivity() {

    val LOG_TAG = "MainActivity"

    companion object {
        val RETURN_CODE = 74
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Pushy.listen(this)
        setSupportActionBar(toolbar)
        supportFragmentManager.load { replace(R.id.base_layout, TimerFragment.newInstance()) }
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_timer -> consume { supportFragmentManager.load { replace(R.id.base_layout, TimerFragment.newInstance()) } }
                else -> true
            }
        }
        if (!isLoggedIn(baseContext)) startActivityForResult(Intent(baseContext, LoginActivity::class.java), RETURN_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return consume { menuInflater.inflate(R.menu.menu_main, menu) }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> consume { startActivity(Intent(baseContext, AboutActivity::class.java)) }
        else -> super.onOptionsItemSelected(item)
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
