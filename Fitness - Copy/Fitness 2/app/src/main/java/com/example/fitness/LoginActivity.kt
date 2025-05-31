package com.example.fitness

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvGoToSignUp = findViewById<TextView>(R.id.tvGoToSignUp)

        sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
            } else {
                // Get the stored password using the prefixed key
                val savedPassword = sharedPrefs.getString("$username.password", null)

                if (savedPassword != null && savedPassword == password) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                    // You can also retrieve other user info if needed
                    val fullName = sharedPrefs.getString("$username.fullName", "")
                    val phoneNumber = sharedPrefs.getString("$username.phoneNumber", "")

                    // Pass user data to MainActivity if needed
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("USERNAME", username)
                        putExtra("FULL_NAME", fullName)
                        putExtra("PHONE_NUMBER", phoneNumber)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvGoToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}