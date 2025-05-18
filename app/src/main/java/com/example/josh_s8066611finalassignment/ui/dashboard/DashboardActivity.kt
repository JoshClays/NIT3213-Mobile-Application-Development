package com.example.josh_s8066611finalassignment.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.ui.details.DetailsActivity
import com.example.josh_s8066611finalassignment.viewmodel.DashboardViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var entityAdapter: EntityAdapter
    private var keypass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        keypass = intent.getStringExtra("KEYPASS") ?: run {
            finish()
            return
        }

        setupViews()
        setupObservers()
        loadData()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        // Initialize adapter with empty list
        entityAdapter = EntityAdapter(emptyList()) { entity ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ENTITY_ITEM", entity)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            setHasFixedSize(true)
            adapter = entityAdapter // Set the adapter immediately
        }

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun setupObservers() {
        viewModel.entities.observe(this) { result ->
            swipeRefreshLayout.isRefreshing = false
            
            result.onSuccess { list ->
                // Create new adapter with the data
                entityAdapter = EntityAdapter(list) { entity ->
                    val intent = Intent(this, DetailsActivity::class.java)
                    intent.putExtra("ENTITY_ITEM", entity)
                    startActivity(intent)
                }
                recyclerView.adapter = entityAdapter
            }.onFailure { error ->
                Log.e("DASHBOARD", "Failed to load dashboard: ${error.message}")
                Snackbar.make(
                    recyclerView,
                    "Failed to load books: ${error.message}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun loadData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.loadDashboard(keypass)
    }
}
