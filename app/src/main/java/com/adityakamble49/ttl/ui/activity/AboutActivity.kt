package com.adityakamble49.ttl.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.adityakamble49.ttl.BuildConfig
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.utils.*
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.card_about_development.*
import kotlinx.android.synthetic.main.card_about_general.*
import kotlinx.android.synthetic.main.card_about_team.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)

        // App Details
        about_version.text = BuildConfig.VERSION_NAME
        about_changelog.setOnClickListener { createChangelogDialog(this).show() }
        about_licenses.setOnClickListener { createLicensesDialog(this).show() }

        // Team
        aboutTeamAkshay.setOnClickListener { openUrl("https://github.com/akshaychordiya/") }
        aboutTeamAditya.setOnClickListener { openUrl("http://www.adityakamble49.com/") }
        aboutTeamPiyush.setOnClickListener { openUrl("https://plus.google.com/104580127666963940082/") }
        aboutTeamMarc.setOnClickListener { openUrl("https://plus.google.com/107192270381957371449/") }

        // Support
        about_rate.setOnClickListener { openUrl("http://play.google.com/store/apps/details?id=" + packageName) }
        about_email.setOnClickListener { sendSupportEmail() }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> consume { finish() }
        else -> super.onOptionsItemSelected(item)
    }
}
