package com.ciyp.domain.usecases

import com.ciyp.domain.MovieRepository
import com.ciyp.domain.entites.Movie
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getNowPlayingMovieList(): Single<List<Movie>> {
        return repository.getNowPlayingMovieList()
    }

    fun getPopularMovieList(page: Int? = null): Single<List<Movie>> {
        return repository.getPopularMovieList(page)
    }

    fun getTopRatedMovieList(): Single<List<Movie>> {
        return repository.getTopRatedMovieList()
    }

    fun getUpcomingMovieList(): Single<List<Movie>> {
        return repository.getUpcomingMovieList()
    }


}