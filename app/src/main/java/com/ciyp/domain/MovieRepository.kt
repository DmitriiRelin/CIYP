package com.ciyp.domain

import com.ciyp.datalayer.remote.dto.genres.listOfGenres.ListOfGenres
import com.ciyp.datalayer.remote.dto.video.ResponseVideo
import com.ciyp.domain.entites.Movie
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getNowPlayingMovieList(): Single<List<Movie>>

    fun getPopularMovieList(page: Int? = null): Single<List<Movie>>

    fun getDetailMovie(id: Int): Single<MovieDetails>

    fun getTopRatedMovieList(): Single<List<Movie>>

    fun getUpcomingMovieList(): Single<List<Movie>>

    fun getVideo(id: Int): Single<ResponseVideo>

    fun getAllLists(): Single<List<CategoryWithListItem>>

    fun getListOfGenres(): Single<ListOfGenres>

    fun getListMovieWithGenre(page: Int? = null, id: Int): Single<List<Movie>>

}