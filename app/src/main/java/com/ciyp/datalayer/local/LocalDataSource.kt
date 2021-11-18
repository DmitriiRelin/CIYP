package com.ciyp.datalayer.local

import com.ciyp.datalayer.local.dto.FavoriteMovie
import com.ciyp.datalayer.local.dto.FavoriteMovieWithCast
import com.ciyp.domain.entites.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao: FavoriteDao) {
    fun getAllMovies(): Single<List<FavoriteMovieWithCast>> {
        return dao.getAllMovies()
    }

    fun insert(movieDetails: MovieDetails): Completable {
        val list = mutableListOf<Completable>()
        list.add(dao.insert(convertMovieDetailsToFavorite(movieDetails)))
        for (cast in movieDetails.casts) {
            list.add(dao.insert(FavoriteCastsTable(cast.id, cast.name, cast.profile_path)))
            list.add(dao.insert(MovieCastTable(movieDetails.id, cast.id)))
        }
        return Completable.concat(list)
    }

    fun delete(movieDetails: MovieDetails): Completable {
        return dao.delete(convertMovieDetailsToFavorite(movieDetails))
    }

    private fun convertMovieDetailsToFavorite(movieDetails: MovieDetails): FavoriteMovie {
        return FavoriteMovie(
            movieId = movieDetails.id,
            title = movieDetails.title,
            poster_path = movieDetails.poster_path,
            release_date = movieDetails.release_date,
            overview = movieDetails.overview,
            runtime = movieDetails.runtime,
            original_title = movieDetails.original_title,
            vote_average = movieDetails.vote_average,
        )
    }
}