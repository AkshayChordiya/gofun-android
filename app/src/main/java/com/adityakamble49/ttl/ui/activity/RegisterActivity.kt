package com.adityakamble49.ttl.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.api.RegisterService
import com.adityakamble49.ttl.model.Token
import com.adityakamble49.ttl.utils.NetworkUtils
import com.adityakamble49.ttl.utils.isNotEmail
import com.adityakamble49.ttl.utils.setToken
import kotlinx.android.synthetic.main.activity_register.*
import me.pushy.sdk.Pushy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val email = register_email.text.toString()
        val password = register_password.text.toString()
        val confirmPassword = register_password_confirm.text.toString()
        if (email.isEmpty() || email.isNotEmail()) {
            register_email_layout.error = getString(R.string.invalid_email_address)
            return
        }
        if (password.isEmpty()) {
            register_password_layout.error = getString(R.string.invalid_password)
            return
        }
        if (password != confirmPassword) {
            register_password_confirm_layout.error = getString(R.string.invalid_confirm_password)
            return
        }

        register_form.visibility = GONE
        register_progress.visibility = VISIBLE

        RegisterForPushNotifications().execute()
    }

    private inner class RegisterForPushNotifications : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void): String? {
            try {
                return Pushy.register(baseContext)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }

            // Success
            return null
        }

        override fun onPostExecute(deviceId: String?) {
            if (deviceId != null) {
                val email = register_email.text
                val password = register_password.text

                val registerService = NetworkUtils.retrofit.create(RegisterService::class.java)
                val register = registerService.register(email.toString(), email.toString(), password.toString(), deviceId)
                register.enqueue(object : Callback<Token> {
                    override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                        if (response != null) {
                            setToken(baseContext, response.body().token)
                            startActivity(Intent(baseContext, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(baseContext, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Token>?, t: Throwable?) {
                        Toast.makeText(baseContext, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}
