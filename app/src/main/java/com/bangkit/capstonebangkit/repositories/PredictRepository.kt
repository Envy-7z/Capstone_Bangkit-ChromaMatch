package com.bangkit.capstonebangkit.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.capstonebangkit.api.ApiService
import com.bangkit.capstonebangkit.models.PredictResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.lang.Exception
import com.bangkit.capstonebangkit.models.Result

class PredictRepository(private val apiService: ApiService) {

    fun postPredict(
        file: MultipartBody.Part,
        rgb1: RequestBody,rgb2: RequestBody,rgb3: RequestBody,rgb4: RequestBody
    ): LiveData<Result<PredictResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postPredict(file, rgb1,rgb2,rgb3,rgb4)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("predict", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

}