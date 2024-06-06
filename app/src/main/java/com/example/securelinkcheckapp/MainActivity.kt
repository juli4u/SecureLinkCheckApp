package com.example.securelinkcheckapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFS_NAME = "UserDetails"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val signInButton = findViewById<Button>(R.id.button)


        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString(KEY_EMAIL, "")
        val savedPassword = sharedPreferences.getString(KEY_PASSWORD, "")

        if (!savedEmail.isNullOrEmpty()) {
            emailEditText.setText(savedEmail)
        }

        if (!savedPassword.isNullOrEmpty()) {
            passwordEditText.setText(savedPassword)
        }

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Save details to shared preferences
                val editor = sharedPreferences.edit()
                editor.putString(KEY_EMAIL, email)
                editor.putString(KEY_PASSWORD, password)
                editor.apply()

                Toast.makeText(this, "Details saved. Signing in...", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LinkScan::class.java))
            }
        }
    }
}

