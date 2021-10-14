package com.bookit.koriramos.di
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class ActivityModule {
	companion object {
		val activityModule = module {
			single { provideSettingsPreferences(androidApplication()) }
//			single { StateManager() }
		}
		private const val PREFERENCES_FILE_KEY = "sharedPrefBookit"
		
		private fun provideSettingsPreferences(app: Application): SharedPreferences =
			app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
	}
}