package com.example.josh_s8066611finalassignment.repository

import com.example.josh_s8066611finalassignment.ApiService
import com.example.josh_s8066611finalassignment.data.model.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val keypass = response.body()?.keypass ?: return Result.failure(Exception("No keypass"))
                Result.success(keypass)
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
