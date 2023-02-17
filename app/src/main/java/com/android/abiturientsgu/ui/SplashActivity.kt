package com.android.abiturientsgu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun onResume() {
        Log.d("OLOLO", "SplashActivity onResume")
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OLOLO", "SplashActivity onCreate")
        setContentView(R.layout.activity_splash)

        // var progressBar = findViewById<ProgressBar>(R.id.progressBar1)

        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                Log.d("OLOLO", "onFinish")
                if (viewModel.currentLogin.isNullOrEmpty()) {
                    Log.d(
                        "OLOLO",
                        "(viewModel.currentLogin==null" + viewModel.currentLogin.toString()
                    )
                    startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
                } else {
                    Log.d("OLOLO", viewModel.currentLogin.toString())

                    startActivity(
                        Intent(
                            this@SplashActivity,
                            MainActivity::class.java
                        ).putExtra("login", viewModel.currentLogin)
                    )
                }
            }

        }.start()


    }
}