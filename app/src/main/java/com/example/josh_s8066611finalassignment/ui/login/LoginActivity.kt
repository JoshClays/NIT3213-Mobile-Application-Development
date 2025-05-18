package com.example.josh_s8066611finalassignment.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.ui.dashboard.DashboardActivity
import com.example.josh_s8066611finalassignment.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

/**
 * LoginActivity handles user authentication using first name and student ID.
 * This activity serves as the entry point of the application.
 * Uses Hilt for dependency injection and follows MVVM architecture pattern.
 */
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // Initialize ViewModel using Hilt dependency injection
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        val firstName = findViewById<TextInputEditText>(R.id.editTextFirstName)
        val studentId = findViewById<TextInputEditText>(R.id.passwordEditText)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val errorText = findViewById<TextView>(R.id.tvError)

        // Set up login button click listener
        loginBtn.setOnClickListener {
            val firstNameText = firstName.text.toString().trim()
            val studentIdText = studentId.text.toString().trim()
            // Validate input fields are not empty before attempting login
            if (firstNameText.isNotEmpty() && studentIdText.isNotEmpty()) {
                viewModel.login(firstNameText, studentIdText)
            }
        }

        // Observe login result from ViewModel
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { keypass ->
                // Hide error message if visible
                errorText.visibility = View.GONE
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // Navigate to Dashboard on successful login
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("KEYPASS", keypass)
                startActivity(intent)
                finish() // Close login activity to prevent back navigation

            }.onFailure {
                // Show error message on login failure
                errorText.visibility = View.VISIBLE
                errorText.text = "Login failed. Please try again."
            }
        }
    }
}
