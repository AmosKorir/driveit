package com.bookit.koriramos

import android.app.Application
import com.bookit.data.di.ApiModule
import com.bookit.data.di.RepositoryModule
import com.bookit.koriramos.di.ActivityModule
import com.bookit.koriramos.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE

class Bookit:Application() {
	
	override fun onCreate() {
		super.onCreate()
		
		startKoin {
			androidLogger(NONE)
			androidContext(applicationContext)
			modules(
				listOf(
					ActivityModule.activityModule,
					ApiModule.module,
					RepositoryModule.repositoryModule,
					ViewModelModule.module
				)
			)
		}
	}
}