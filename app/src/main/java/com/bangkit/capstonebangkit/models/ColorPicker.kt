package com.bangkit.capstonebangkit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ColorPicker(
    val id: Int,
    val valueColors: String,
) : Parcelable