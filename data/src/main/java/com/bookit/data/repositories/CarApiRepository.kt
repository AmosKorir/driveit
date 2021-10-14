package com.bookit.data.repositories

import com.bookit.data.R
import com.bookit.data.api.AuthApi
import com.bookit.domain.Car
import com.bookit.domain.CarRepository
import com.bookit.domain.User
import io.reactivex.Single

class CarApiRepository(private val authApi: AuthApi) : CarRepository {
	// mock api Response data
	override fun getCars(): Single<List<Car>> {
		val car = Car(R.drawable.car4, "Toyota Max X", "AED 230","Monthly","Per month")
		val car1 = Car(R.drawable.car2, "Honda Amaze", "AED 25.56","Daily/Monthly","Per day")
		val car2 = Car(R.drawable.car, "Nissan bluebird", "AED 230","Daily","Per day")
		val car3 = Car(R.drawable.car3, "Audi Q7", "AED 286","Monthly","Per month")
		val list = listOf<Car>(car, car1, car2, car3)
		return Single.just(list)
	}
	
	override fun loginUser(userName: String, password: String): Single<String> {
		return authApi.loginUSer(userName, password)
			.map { it.token }
	}
	
	override fun getUser(): Single<User> = authApi.getUser()
		.map { it.data }
		.map {
			with(it) {
				return@map User(avatar, email, firstName, id, lastName)
			}
		}
	
}