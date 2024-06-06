package com.example.securelinkcheckapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class LinkScan : AppCompatActivity() {

    private val safeNetApiKey = "AIzaSyAkKJlQQE2FzeoOkWykqmGLnBRhhp8j7pU"
    private val safeNetUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=$safeNetApiKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_scan)

        val enterLinkEditText = findViewById<EditText>(R.id.enterLink)
        val scanLinkButton = findViewById<Button>(R.id.scanLinkbtn)

        scanLinkButton.setOnClickListener {
            val link = enterLinkEditText.text.toString()
            if (link.isEmpty()) {
                Toast.makeText(this, "Please enter a link to scan", Toast.LENGTH_SHORT).show()
            } else {
                // Make API request to check if the link is safe
                checkLinkSafety(link)
            }
        }
    }

    private fun checkLinkSafety(link: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val isSafe = isLinkSafe(link)
            withContext(Dispatchers.Main) {
                if (isSafe) {
                    // Link is safe, navigate to IfLinkSafe activity
                    startActivity(Intent(this@LinkScan, IfLinkSafe::class.java))
                } else {
                    // Link is unsafe, navigate to IfLinkUnsafe activity
                    startActivity(Intent(this@LinkScan, IfLinkUnsafe::class.java))
                }
            }
        }
    }

    private suspend fun isLinkSafe(link: String): Boolean {
        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), "{\"client\": {\"clientId\": \"YOUR_CLIENT_ID\", \"clientVersion\": \"1.0.0\"},\"threatInfo\": {\"threatTypes\": [\"MALWARE\", \"SOCIAL_ENGINEERING\", \"UNWANTED_SOFTWARE\", \"POTENTIALLY_HARMFUL_APPLICATION\"],\"platformTypes\": [\"ANY_PLATFORM\"],\"threatEntryTypes\": [\"URL\"],\"threatEntries\": [{\"url\": \"$link\"}]}}")
        val request = Request.Builder().url(safeNetUrl).post(requestBody).build()

        val client = OkHttpClient()
        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string()
            val jsonObject = JSONObject(responseData)
            if (jsonObject.has("matches")) {
                val matchesArray: JSONArray = jsonObject.getJSONArray("matches")
                if (matchesArray.length() > 0) {
                    return false // Link is unsafe
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return true // Link is safe
    }
}
