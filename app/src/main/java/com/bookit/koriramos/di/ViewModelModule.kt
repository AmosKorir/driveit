package com.bookit.koriramos.di

import com.bookit.koriramos.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

object ViewModelModule {
	val module = org.koin.dsl.module {
		viewModel {
			MainViewModel(get())
		}
	}
}