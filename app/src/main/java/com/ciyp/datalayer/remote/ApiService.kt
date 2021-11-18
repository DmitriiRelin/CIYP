package com.ciyp.datalayer.remote

import com.ciyp.datalayer.remote.dto.ResponseResult
import com.ciyp.datalayer.remote.dto.detail.MovieDetailResponse
import com.ciyp.datalayer.remote.dto.detail.crew.MovieCrewResponse
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.ListOfGenres
import com.ciyp.datalayer.remote.dto.genres.listOfSpecificGenres.ListOfSpecificGenres
import com.ciyp.datalayer.remote.dto.video.ResponseVideo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/now_playing")
    fun getMovieListNowPlaying(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<ResponseResult>

    @GET("3/movie/popular")
    fun getMovieListPopular(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
        @Query(PAGE) page: Int? = null,
    ): Single<ResponseResult>

    @GET("3/movie/top_rated")
    fun getMovieTopRated(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<ResponseResult>

    @GET("3/movie/upcoming")
    fun getMovieUpcoming(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<ResponseResult>


    @GET("3/movie/{movie_id}/videos")
    fun getMovieVideo(
        @Path("movie_id") id: Int,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<ResponseVideo>


    @GET("3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<MovieDetailResponse>

    @GET("3/movie/{movie_id}/credits")
    fun getCrewForMovie(
        @Path(QUERY_MOVIE_ID) id: Int,
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<MovieCrewResponse>


    @GET("3/genre/movie/list")
    fun getAllGenres(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
    ): Single<ListOfGenres>

    @GET("3/discover/movie")
    fun getListMovieWithGenre(
        @Query(QUERY_LANGUAGE) language: String = LANGUAGE_ENG,
        @Query(QUERY_API_KEY) key: String,
        @Query(QUERY_PARAM_PAGE) page: Int? = null,
        @Query(QUERY_PARAM_LIST_OF_GENRE) with_genres: Int,
    ): Single<ListOfSpecificGenres>


    companion object {
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_MOVIE_ID = "movie_id"
        private const val LANGUAGE_ENG = "eng-ENG"
        private const val PAGE = "page"
        private const val QUERY_PARAM_PAGE = "with_genres"
        private const val QUERY_PARAM_LIST_OF_GENRE = "with_genres"
    }
}