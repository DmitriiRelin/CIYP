package com.ciyp.domain.usecases

import com.ciyp.domain.MovieRepository
import com.ciyp.domain.entites.Movie
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMovieListWithGenreUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getListMovieWithGenre(page: Int? = null, id: Int): Single<List<Movie>> {
        return repository.getListMovieWithGenre(page, id)
    }
}