package com.adityakamble49.ttl.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.adityakamble49.ttl.R
import com.adityakamble49.ttl.api.LoginService
import com.adityakamble49.ttl.model.Token
import com.adityakamble49.ttl.utils.NetworkUtils.Companion.retrofit
import com.adityakamble49.ttl.utils.isNotEmail
import com.adityakamble49.ttl.utils.setToken
import kotlinx.android.synthetic.main.activity_login.*
import me.pushy.sdk.Pushy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class LoginActivity : AppCompatActivity() {

    val LOG_TAG = "LoginActivity"
    val PERMISSION_REQUEST_STORAGE = 124

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestPermission()
        login_button.setOnClickListener { doLogin() }
        go_to_register.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
            finish()
        }
    }

    fun doLogin() {
        val email = login_email.text.toString()
        val password = login_password.text.toString()
        if (email.isEmpty() || email.isNotEmail()) {
            login_email_layout.error = getString(R.string.invalid_email_address)
            return
        }
        if (password.isEmpty()) {
            login_password_layout.error = getString(R.string.invalid_password)
            return
        }

        login_form.visibility = GONE
        login_progress.visibility = VISIBLE

        RegisterForPushNotifications().execute()
    }

    fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                // TODO: Show explanation

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSION_REQUEST_STORAGE)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    /*override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_STORAGE -> Pushy.listen(this)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }*/

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
                val email = login_email.text
                val password = login_password.text

                val loginService = retrofit.create(LoginService::class.java)
                val login = loginService.login(email.toString(), password.toString(), deviceId)
                login.enqueue(object : Callback<Token> {
                    override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
                        if (response != null) {
                            setToken(baseContext, response.body().token)
                            setResult(Activity.RESULT_OK)
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
