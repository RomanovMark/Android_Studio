package com.example.launchamap

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Show a map with implicit intent

    }
    fun showMap(v: View) {
        // get latitude and longitude values
        val latEditText = findViewById<EditText>(R.id.latEditText)
        val lngEditText = findViewById<EditText>(R.id.lngEditText)
        val lat = latEditText.text.toString().toDouble()
        val lng = lngEditText.text.toString().toDouble()

        // Build the intent
        val location = Uri.parse("geo:$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW, location)

        // Try to invoke the intent.
        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            // Define what your app should do if no activity can handle the intent.
            Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show()
        }
    }
}