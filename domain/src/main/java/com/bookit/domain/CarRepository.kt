package com.bookit.domain

import io.reactivex.Single

interface CarRepository {
	fun getCars(): Single<List<Car>>
	
    fun loginUser(userName: String, password: String): Single<String>
    
    fun getUser():Single<User>
}