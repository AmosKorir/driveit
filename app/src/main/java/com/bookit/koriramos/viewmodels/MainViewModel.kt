package com.bookit.koriramos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bookit.domain.Car
import com.bookit.domain.CarRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val carRepository: CarRepository) : ViewModel() {
	private val compositeDisposable = CompositeDisposable()
	val handleError = MutableLiveData<Throwable>()
	
	private val _availableCars = MutableLiveData<List<Car>>()
	val cars get() = _availableCars
	fun getAvailableCars() {
		val disposable = carRepository.getCars()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				{
					_availableCars.value = it
				}, { handleError.value = it }
			)
		compositeDisposable.add(disposable)
	}
	
	private val _loginSuccess = MutableLiveData<String>()
	val loginSuccess get() = _loginSuccess
	fun loginUser(userName: String, password: String) {
		val disposable = carRepository.loginUser(userName, password)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				{
					_loginSuccess.value = it
				}, { handleError.value = it }
			)
		compositeDisposable.add(disposable)
	}
	
	fun getUser() {
		val disposable = carRepository.getUser()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				{
				
				}, { handleError.value = it }
			)
		compositeDisposable.add(disposable)
	}
}