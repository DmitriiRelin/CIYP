package com.ciyp.datalayer.local

import androidx.room.Entity
import androidx.room.ForeignKey
import com.ciyp.datalayer.local.dto.FavoriteMovie

@Entity(
    tableName = "FavoriteMovieCast",
    foreignKeys = [
        ForeignKey(
            entity = FavoriteMovie::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FavoriteCastsTable::class,
            parentColumns = ["castId"],
            childColumns = ["castId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["movieId", "castId"]
)
data class MovieCastTable(
    val movieId: Int,
    val castId: Int,
)