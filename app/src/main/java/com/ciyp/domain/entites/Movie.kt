package com.ciyp.domain.entites

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date : String,
) : Parcelable