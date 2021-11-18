package com.ciyp.domain.usecases

import com.ciyp.domain.MovieRepository
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getDetailMovie(id: Int): Single<MovieDetails> {
        return repository.getDetailMovie(id)
    }

}