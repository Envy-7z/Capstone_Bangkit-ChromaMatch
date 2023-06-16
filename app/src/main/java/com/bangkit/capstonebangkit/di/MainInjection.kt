package com.bangkit.capstonebangkit.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bangkit.capstonebangkit.api.ApiConfig
import com.bangkit.capstonebangkit.repositories.PredictRepository


object MainInjection {
    fun provideRepository(context: Context): PredictRepository {
        val apiService = ApiConfig.getApiClient()
        return PredictRepository(apiService)
    }
}