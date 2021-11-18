package com.ciyp.datalayer.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteFilms")
data class FavoriteMovie(
    @PrimaryKey
    val movieId: Int,
    val title: String,
    val poster_path: String,
    val release_date: String,
    val overview: String,
    val runtime: Int,
    val original_title: String,
    val vote_average: Double,
)