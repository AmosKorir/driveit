package com.bookit.data.api

import com.bookit.data.apiResponse.ApiResponse
import com.bookit.data.apiResponse.UserApiResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
	@FormUrlEncoded
	@POST("login")
	fun loginUSer(
		@Field("email") email: String,
		@Field("password") password: String
	): Single<ApiResponse>
	@GET("users/2")
 fun getUser(): Single<UserApiResponse>
	
}