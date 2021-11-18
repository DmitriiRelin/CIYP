package com.ciyp.datalayer.local

import androidx.room.*
import com.ciyp.datalayer.local.dto.FavoriteMovie
import com.ciyp.datalayer.local.dto.FavoriteMovieWithCast
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteDao {
    @Transaction
    @Query("SELECT * FROM FavoriteFilms")
    fun getAllMovies(): Single<List<FavoriteMovieWithCast>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteMovie: FavoriteMovie): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteCast: FavoriteCastsTable): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieCastTable: MovieCastTable): Completable

    @Delete()
    fun delete(favoriteMovie: FavoriteMovie): Completable

}