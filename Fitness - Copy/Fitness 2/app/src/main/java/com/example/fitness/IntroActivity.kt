package com.example.fitness

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnHowItWorks = findViewById<Button>(R.id.btnHowItWorks)
        val btnLetsGetStarted = findViewById<Button>(R.id.btnLetsGetStarted)

        btnHowItWorks.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("How it Works")
                .setMessage("This app helps you track workouts, meals, and progress. Start your fitness journey now!")
                .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        btnLetsGetStarted.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}