package com.bangkit.capstonebangkit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
    val season_from_color: String,
    val tone_from_color: String,
    val tone_from_image: String
) : Parcelable