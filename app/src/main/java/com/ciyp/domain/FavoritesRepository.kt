package com.ciyp.domain

import com.ciyp.domain.entites.Movie
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FavoritesRepository {

    fun addToFavorite(movie: MovieDetails): Completable

    fun deleteFromFavorite(movie: MovieDetails): Completable

    fun deleteFromFavorite(movie: Movie): Completable

    fun getFavoriteList(): Single<List<MovieDetails>>


}