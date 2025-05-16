package com.example.josh_s8066611finalassignment.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keypass = intent.getStringExtra("KEYPASS") ?: return

        // Optional layout for now
        setContentView(R.layout.activity_dashboard)

        viewModel.loadDashboard(keypass)

        viewModel.entities.observe(this) { result ->
            result.onSuccess { list ->
                list.forEachIndexed { index, item ->
                    Log.d("DASHBOARD", "[$index] ${item.title} | ${item.author}")
                }
            }

            result.onFailure {
                Log.e("DASHBOARD", "Failed to load dashboard: ${it.message}")
            }
        }
    }
}
