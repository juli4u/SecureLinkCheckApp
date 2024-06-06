package com.example.securelinkcheckapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenSecureLinkCheck : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_secure_link_check)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LaunchAppSecureLinkCheck::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}

