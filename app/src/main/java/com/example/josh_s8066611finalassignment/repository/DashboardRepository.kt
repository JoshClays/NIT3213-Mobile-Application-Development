package com.example.josh_s8066611finalassignment.repository

import android.util.Log
import com.example.josh_s8066611finalassignment.data.api.ApiService
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDashboard(keypass: String): Result<List<EntityItem>> {
        return try {
            Log.d("DashboardRepository", "Fetching dashboard with keypass: $keypass")
            val response = apiService.getDashboard(keypass)
            Log.d("DashboardRepository", "Request URL: ${response.raw().request.url}")
            Log.d("DashboardRepository", "Request headers: ${response.raw().request.headers}")
            
            if (response.isSuccessful) {
                val dashboardResponse = response.body()
                Log.d("DashboardRepository", "Dashboard fetch successful, total items: ${dashboardResponse?.entityTotal}")
                Result.success(dashboardResponse?.entities ?: emptyList())
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = "Failed to fetch dashboard: ${response.code()}, error: $errorBody"
                Log.e("DashboardRepository", errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Log.e("DashboardRepository", "Dashboard fetch exception", e)
            Result.failure(e)
        }
    }
}
