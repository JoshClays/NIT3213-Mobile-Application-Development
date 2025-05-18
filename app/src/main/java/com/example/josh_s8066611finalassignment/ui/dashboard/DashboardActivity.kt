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

/**
 * DashboardActivity displays the list of books in a RecyclerView.
 * Features include:
 * - Pull-to-refresh functionality
 * - Book item click navigation to details
 * - Error handling with Snackbar
 * Uses Material 3 design components and follows MVVM architecture.
 */
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

        // Get keypass from intent, finish activity if not provided
        keypass = intent.getStringExtra("KEYPASS") ?: run {
            finish()
            return
        }

        setupViews()
        setupObservers()
        loadData()
    }

    /**
     * Initializes and configures the UI components:
     * - RecyclerView with LinearLayoutManager
     * - SwipeRefreshLayout for pull-to-refresh
     * - EntityAdapter for displaying book items
     */
    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        // Initialize adapter with empty list
        entityAdapter = EntityAdapter(emptyList()) { entity ->
            // Navigate to details screen on item click
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ENTITY_ITEM", entity)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            setHasFixedSize(true)
            adapter = entityAdapter
        }

        // Configure pull-to-refresh
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    /**
     * Sets up observers for the ViewModel's LiveData:
     * - Updates the UI when new data is received
     * - Handles success and error states
     * - Shows error messages using Snackbar
     */
    private fun setupObservers() {
        viewModel.entities.observe(this) { result ->
            swipeRefreshLayout.isRefreshing = false
            
            result.onSuccess { list ->
                // Update adapter with new data
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

    /**
     * Loads dashboard data from the ViewModel
     * Shows loading indicator while data is being fetched
     */
    private fun loadData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.loadDashboard(keypass)
    }
}
