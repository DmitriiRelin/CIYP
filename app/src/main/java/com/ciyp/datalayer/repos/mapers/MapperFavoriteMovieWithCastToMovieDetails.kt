package com.ciyp.datalayer.repos.mapers

import com.ciyp.datalayer.local.dto.FavoriteMovieWithCast
import com.ciyp.domain.entites.Cast
import com.ciyp.domain.entites.MovieDetails
import javax.inject.Inject

class MapperFavoriteMovieWithCastToMovieDetails @Inject constructor() :
    Mapper<FavoriteMovieWithCast, MovieDetails> {

    override fun map(input: FavoriteMovieWithCast): MovieDetails {
        return MovieDetails(
            id = input.movieId,
            title = input.title,
            poster_path = input.poster_path,
            casts = input.casts.map { FavoriteCastTable ->
                Cast(FavoriteCastTable.castId, FavoriteCastTable.name, FavoriteCastTable.profile_path)
            },
            isInFavorite = true,
            release_date = input.release_date,
            overview = input.overview,
            runtime = input.runtime,
            original_title = input.original_title,
            vote_average = input.vote_average
        )
    }
}


class ListMapperFavoriteMovieWithCastToMovieDetails @Inject constructor(
    private val mapper: Mapper<FavoriteMovieWithCast, MovieDetails>,
) : ListMapper<FavoriteMovieWithCast, MovieDetails> {
    override fun map(input: List<FavoriteMovieWithCast>): List<MovieDetails> {
        return input.map { mapper.map(it) }
    }
}