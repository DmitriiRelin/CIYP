package com.ciyp.datalayer.remote

import com.ciyp.BuildConfig
import com.ciyp.datalayer.remote.dto.ResponseResult
import com.ciyp.datalayer.remote.dto.detail.MovieDetailResponse
import com.ciyp.datalayer.remote.dto.detail.crew.MovieCrewResponse
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.ListOfGenres
import com.ciyp.datalayer.remote.dto.genres.listOfSpecificGenres.ListOfSpecificGenres
import com.ciyp.datalayer.remote.dto.video.ResponseVideo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val cinemaApi: ApiService) {

    companion object {
        private const val LANGUAGE_FOR_REQUEST = "eng-ENG"
    }

    fun getVideo(id: Int): Single<ResponseVideo> {
        return cinemaApi.getMovieVideo(id, LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }


    fun getNowPlayingMovieList(): Single<ResponseResult> {
        return cinemaApi.getMovieListNowPlaying(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

    fun getPopularMovieList(page: Int? = null): Single<ResponseResult> {
        return cinemaApi.getMovieListPopular(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key, page)
    }


    fun getTopRatedMovieList(): Single<ResponseResult> {
        return cinemaApi.getMovieTopRated(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

    fun getUpcomingMovieList(): Single<ResponseResult> {
        return cinemaApi.getMovieUpcoming(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

    fun getListOfGenres(): Single<ListOfGenres> {
        return cinemaApi.getAllGenres(LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

    fun getListMovieWithGenre(page: Int? = null, id: Int): Single<ListOfSpecificGenres> {
        return cinemaApi.getListMovieWithGenre(LANGUAGE_FOR_REQUEST,
            BuildConfig.mdp_api_key,
            page,
            id)
    }

    fun getDetailMovie(id: Int): Single<MovieDetailResponse> {
        return cinemaApi.getMovieDetails(id, LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

    fun getCrewCinema(id: Int): Single<MovieCrewResponse> {
        return cinemaApi.getCrewForMovie(id, LANGUAGE_FOR_REQUEST, BuildConfig.mdp_api_key)
    }

}