package com.example.fitness

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etNewUsername = findViewById<EditText>(R.id.etNewUsername)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)

        sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnSignUp.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val newUsername = etNewUsername.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()

            if (fullName.isEmpty() || phoneNumber.isEmpty() || newUsername.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                if (sharedPrefs.contains(newUsername)) {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                } else {
                    // Store user data (you might want to use a more secure way to store passwords)
                    val editor = sharedPrefs.edit()
                    editor.putString("$newUsername.username", newUsername)
                    editor.putString("$newUsername.password", newPassword)
                    editor.putString("$newUsername.fullName", fullName)
                    editor.putString("$newUsername.phoneNumber", phoneNumber)
                    editor.apply()

                    Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }

        tvGoToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}