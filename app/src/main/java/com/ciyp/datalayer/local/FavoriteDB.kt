package com.ciyp.datalayer.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ciyp.datalayer.local.dto.FavoriteMovie

@Database(entities = [FavoriteMovie::class, FavoriteCastsTable::class, MovieCastTable::class],
    version = 1,
    exportSchema = false)
abstract class FavoriteDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}