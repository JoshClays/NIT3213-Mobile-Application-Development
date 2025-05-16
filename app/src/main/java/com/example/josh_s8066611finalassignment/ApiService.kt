package com.example.josh_s8066611finalassignment

import com.example.josh_s8066611finalassignment.data.model.DashboardResponse
import com.example.josh_s8066611finalassignment.data.model.LoginRequest
import com.example.josh_s8066611finalassignment.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @POST("sydney/auth")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(@Path("keypass") keypass: String): Response<DashboardResponse>
}
