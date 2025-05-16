package com.example.josh_s8066611finalassignment

import com.example.josh_s8066611finalassignment.data.model.LoginRequest
import com.example.josh_s8066611finalassignment.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
