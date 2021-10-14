package com.bookit.data.di

import com.bookit.data.repositories.CarApiRepository
import com.bookit.domain.CarRepository
import org.koin.dsl.module

object RepositoryModule {
	val repositoryModule = module {
		single {
			CarApiRepository(get()) as CarRepository
		}
	}
}