package com.bangkit.capstonebangkit.ui.viewModels

import androidx.lifecycle.ViewModel
import com.bangkit.capstonebangkit.repositories.PredictRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PredictViewModel(private val repository: PredictRepository) : ViewModel() {

    fun postPredict(
        file: MultipartBody.Part,
        rgb1: RequestBody,
        rgb2: RequestBody,
        rgb3: RequestBody,
        rgb4: RequestBody
    ) =
        repository.postPredict(file, rgb1, rgb2, rgb3, rgb4)

}