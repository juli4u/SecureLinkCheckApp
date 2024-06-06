package com.example.securelinkcheckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IfLinkSafe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_if_link_safe)
        val backBtn = findViewById<Button>(R.id.backbtn)
        backBtn.setOnClickListener {
            startActivity(Intent(this, LaunchAppSecureLinkCheck::class.java))
            finish()
        }
    }
}