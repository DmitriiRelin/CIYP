package com.ciyp.datalayer.repos.mapers

import com.ciyp.datalayer.remote.dto.detail.MovieDetailResponse
import com.ciyp.datalayer.remote.dto.detail.crew.MovieCrewResponse
import com.ciyp.domain.entites.Cast
import com.ciyp.domain.entites.MovieDetails
import javax.inject.Inject


class MapperResponsesToMovieDetails @Inject constructor() :
    Mapper<MapperResponsesToMovieDetails.Responses, MovieDetails> {

    data class Responses(
        val movieDetails: MovieDetailResponse,
        val crew: MovieCrewResponse,
        val isInFavorite: Boolean,
    )

    override fun map(input: Responses): MovieDetails {
        return MovieDetails(
            id = input.movieDetails.id,
            title = input.movieDetails.title,
            poster_path = input.movieDetails.poster_path,
            casts = input.crew.castFromApi.map { Cast(it.id, it.name, it.profile_path) },
            isInFavorite = input.isInFavorite,
            release_date = input.movieDetails.release_date,
            overview = input.movieDetails.overview,
            runtime = input.movieDetails.runtime,
            original_title = input.movieDetails.original_title,
            vote_average = input.movieDetails.vote_average
        )
    }
}