package com.ciyp.domain.usecases

import com.ciyp.domain.FavoritesRepository
import com.ciyp.domain.entites.Movie
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    fun deleteMovie(movie: MovieDetails): Completable {
        return favoritesRepository.deleteFromFavorite(movie)
    }

    fun deleteMovie(movie: Movie): Completable {
        return favoritesRepository.deleteFromFavorite(movie)
    }

}