package com.ciyp.datalayer.local.dto

import androidx.room.Junction
import androidx.room.Relation
import com.ciyp.datalayer.local.FavoriteCastsTable
import com.ciyp.datalayer.local.MovieCastTable

data class FavoriteMovieWithCast(
    var movieId: Int = 0,
    var title: String = "",
    val poster_path: String,
    val release_date: String,
    val overview: String,
    val runtime: Int,
    val original_title: String,
    val vote_average: Double,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "castId",
        associateBy = Junction(MovieCastTable::class)
    )
    var casts: List<FavoriteCastsTable> = emptyList(),
)