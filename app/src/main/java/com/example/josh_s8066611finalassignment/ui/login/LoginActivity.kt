package com.example.josh_s8066611finalassignment.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.ui.dashboard.DashboardActivity
import com.example.josh_s8066611finalassignment.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.editTextFirstName)
        val password = findViewById<TextInputEditText>(R.id.passwordEditText)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val errorText = findViewById<TextView>(R.id.tvError)

        loginBtn.setOnClickListener {
            val user = username.text.toString().trim()
            val pass = password.text.toString().trim()
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.login(user, pass)
            }
        }

        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { keypass ->
                errorText.visibility = View.GONE
                Toast.makeText(this, "Login Success. Keypass: $keypass", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("KEYPASS", keypass)
                startActivity(intent)

            }
            result.onFailure {
                errorText.visibility = View.VISIBLE
            }
        }
    }
}
