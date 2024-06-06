package com.example.securelinkcheckapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LaunchAppSecureLinkCheck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_app_secure_link_check)

        val startScanButton = findViewById<Button>(R.id.ScanButton)
        startScanButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
