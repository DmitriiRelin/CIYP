package com.ciyp.domain.usecases

import com.ciyp.domain.FavoritesRepository
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class AddFavoriteMovieUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun addToFavorite(movie: MovieDetails): Completable {
        return favoritesRepository.addToFavorite(movie)
    }

}