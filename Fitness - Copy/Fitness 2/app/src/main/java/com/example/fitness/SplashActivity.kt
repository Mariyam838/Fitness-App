package com.example.fitness

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPrefs: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        if (sharedPrefs.getBoolean("dark_mode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val appNameText = findViewById<TextView>(R.id.tvAppName)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        welcomeText.startAnimation(fadeIn)
        appNameText.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}