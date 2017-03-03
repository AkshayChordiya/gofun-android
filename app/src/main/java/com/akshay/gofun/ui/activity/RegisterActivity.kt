package com.akshay.gofun.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akshay.gofun.R
import kotlinx.android.synthetic.main.activity_register.*

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_button.setOnClickListener { doRegister() }
        go_to_login.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }

    fun doRegister() {
    }
}
