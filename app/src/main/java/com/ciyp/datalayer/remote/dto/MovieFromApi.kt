package com.ciyp.datalayer.remote.dto

data class MovieFromApi(
    val id: Int,
    val title: String,
    val poster_path: String,
    val vote_average: Double,
    val release_date : String,
)