package com.bangkit.capstonebangkit.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.capstonebangkit.di.MainInjection
import com.bangkit.capstonebangkit.repositories.PredictRepository
import com.bangkit.capstonebangkit.ui.viewModels.PredictViewModel

class ViewModelFactory(private val repository: PredictRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PredictViewModel::class.java) -> PredictViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(MainInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}