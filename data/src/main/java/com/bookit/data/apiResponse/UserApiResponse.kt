package com.bookit.data.apiResponse


import com.google.gson.annotations.SerializedName

data class UserApiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("support")
    val support: Support
)