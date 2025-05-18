package com.example.josh_s8066611finalassignment.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val firstName: String,
    @SerializedName("password")
    val studentId: String
) {
    override fun toString(): String {
        return "LoginRequest(username=$firstName, password=$studentId)"
    }
}
