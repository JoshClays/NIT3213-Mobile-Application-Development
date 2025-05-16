package com.example.josh_s8066611finalassignment.repository

import com.example.josh_s8066611finalassignment.ApiService
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDashboard(keypass: String): Result<List<EntityItem>> {
        return try {
            val response = apiService.getDashboardData(keypass)
            if (response.isSuccessful) {
                Result.success(response.body()?.entities ?: emptyList())
            } else {
                Result.failure(Exception("Dashboard fetch failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
