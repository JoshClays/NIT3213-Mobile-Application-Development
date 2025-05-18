package com.example.josh_s8066611finalassignment.repository

import android.util.Log
import com.example.josh_s8066611finalassignment.data.api.ApiService
import com.example.josh_s8066611finalassignment.data.model.LoginRequest
import javax.inject.Inject

/**
 * LoginRepository handles data operations for user authentication.
 * It communicates with the remote API service to perform login operations.
 * Uses Retrofit for network calls and implements error handling.
 */
class LoginRepository @Inject constructor(private val apiService: ApiService) {
    
    /**
     * Attempts to authenticate user with the remote API.
     * @param firstName The user's first name
     * @param studentId The user's student ID
     * @return Result<String> containing the keypass on success or an error on failure
     */
    suspend fun login(firstName: String, studentId: String): Result<String> {
        return try {
            // Log attempt details for debugging
            Log.d("LoginRepository", "Attempting login with firstName: $firstName, studentId: $studentId")
            val request = LoginRequest(firstName = firstName, studentId = studentId)
            Log.d("LoginRepository", "Request body: $request")
            
            // Make API call and log request details
            val response = apiService.login(request)
            Log.d("LoginRepository", "Request URL: ${response.raw().request.url}")
            Log.d("LoginRepository", "Request headers: ${response.raw().request.headers}")
            Log.d("LoginRepository", "Request method: ${response.raw().request.method}")
            Log.d("LoginRepository", "Request body: ${response.raw().request.body}")
            
            if (response.isSuccessful) {
                // Handle successful response
                val body = response.body()
                Log.d("LoginRepository", "Login successful, response: $body")
                Result.success(body?.keypass ?: "Login successful")
            } else {
                // Handle API error response
                val errorBody = response.errorBody()?.string()
                val errorMsg = "Login failed with code: ${response.code()}, error: $errorBody, url: ${response.raw().request.url}"
                Log.e("LoginRepository", errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            // Handle network or other exceptions
            Log.e("LoginRepository", "Login exception", e)
            Result.failure(e)
        }
    }
}
