package com.example.securelinkcheckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InstructionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction_page)

        val closeButton = findViewById<Button>(R.id.closebtn)
        closeButton.setOnClickListener {
            finish()
        }
    }
}
