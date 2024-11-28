package com.tural.portifolio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ensuring the UI takes system insets into account for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gmail = findViewById<TextView>(R.id.textView2)
        gmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:turalaliyev0@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Hello Tural!")
            }

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No email client installed on this device.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val phoneTextView: TextView = findViewById(R.id.textView3)
        phoneTextView.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+994515807313")
            }
            startActivity(phoneIntent)
        }


        val locationTextView: TextView = findViewById(R.id.textView4)
        locationTextView.setOnClickListener {
            val geoUri = "geo:40,-40?q=Baku, Azerbaijan"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri)).apply {
                setPackage("com.google.android.apps.maps")
            }
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                startActivity(fallbackIntent)

            }
        }
        val playButton = findViewById<Button>(R.id.button)

        playButton.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.msuic) //
                mediaPlayer?.start() // Start playing music
            }
        }
    }
}
