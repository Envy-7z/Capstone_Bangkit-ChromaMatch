package com.bangkit.capstonebangkit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HistoryResponse(
    val id: Int,
    val name: String,
    val date : String,
    val image : String
) : Parcelable
