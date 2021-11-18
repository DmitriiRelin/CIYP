package com.ciyp.datalayer.repos

import com.ciyp.datalayer.local.LocalDataSource
import com.ciyp.datalayer.local.dto.FavoriteMovieWithCast
import com.ciyp.domain.FavoritesRepository
import com.ciyp.domain.entites.Movie
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.datalayer.repos.mapers.ListMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val mapper: ListMapper<FavoriteMovieWithCast, MovieDetails>,
) :
    FavoritesRepository {

    override fun addToFavorite(movieDetails: MovieDetails): Completable {
        return localDataSource.insert(movieDetails)
    }

    override fun deleteFromFavorite(movie: MovieDetails): Completable {
        return localDataSource.delete(movie)
    }

    override fun deleteFromFavorite(movie: Movie): Completable {
        TODO("Not yet implemented")
    }


    override fun getFavoriteList(): Single<List<MovieDetails>> {
        return localDataSource.getAllMovies().map { list ->
            mapper.map(list)
        }
    }
}