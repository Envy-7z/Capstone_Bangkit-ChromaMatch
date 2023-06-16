package com.bangkit.capstonebangkit.api

import com.bangkit.capstonebangkit.models.PredictResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("predict")
    suspend fun postPredict(
        @Part file: MultipartBody.Part,
        @Part("rgb1") rgb1: RequestBody,
        @Part("rgb2") rgb2: RequestBody,
        @Part("rgb3") rgb3: RequestBody,
        @Part("rgb4") rgb4: RequestBody,
    ): PredictResponse


}