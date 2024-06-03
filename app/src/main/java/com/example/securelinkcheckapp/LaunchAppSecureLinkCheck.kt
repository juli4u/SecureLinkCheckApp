package com.example.securelinkcheckapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class LaunchAppSecureLinkCheck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_app_secure_link_check)

        val scanButton: Button = findViewById(R.id.ScanButton)
        scanButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
