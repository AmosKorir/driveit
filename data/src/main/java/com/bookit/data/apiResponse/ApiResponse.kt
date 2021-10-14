package com.bookit.data.apiResponse


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("token")
    val token: String
)