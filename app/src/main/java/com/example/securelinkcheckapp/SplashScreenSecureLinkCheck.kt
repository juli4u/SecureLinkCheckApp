package com.example.securelinkcheckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenSecureLinkCheck : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_secure_link_check)
        android.os.Handler().postDelayed({
            startActivity(Intent(this, LaunchAppSecureLinkCheck::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
