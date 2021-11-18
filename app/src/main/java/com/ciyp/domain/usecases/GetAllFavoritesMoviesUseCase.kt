package com.ciyp.domain.usecases

import com.ciyp.domain.FavoritesRepository
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllFavoritesMoviesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun getAllFavoritesMovies(): Single<List<MovieDetails>> {
        return favoritesRepository.getFavoriteList()
    }
}