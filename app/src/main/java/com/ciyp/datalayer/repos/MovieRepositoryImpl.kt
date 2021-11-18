package com.ciyp.datalayer.repos

import com.ciyp.datalayer.local.LocalDataSource
import com.ciyp.datalayer.remote.RemoteDataSource
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.ListOfGenres
import com.ciyp.datalayer.remote.dto.video.ResponseVideo
import com.ciyp.domain.MovieRepository
import com.ciyp.domain.entites.Movie
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.ui.home.homeRecyclerView.base.CategoryWithListItem
import com.ciyp.datalayer.repos.mapers.Mapper
import com.ciyp.datalayer.repos.mapers.MapperResponsesToMovieDetails
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: Mapper<MapperResponsesToMovieDetails.Responses, MovieDetails>,
) : MovieRepository {

    override fun getNowPlayingMovieList(): Single<List<Movie>> {
        return dataSource.getNowPlayingMovieList().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(movieFromApi.id, movieFromApi.title, movieFromApi.poster_path, movieFromApi.vote_average, movieFromApi.release_date)
            }
        }
    }

    override fun getTopRatedMovieList(): Single<List<Movie>> {
        return dataSource.getTopRatedMovieList().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(movieFromApi.id, movieFromApi.title, movieFromApi.poster_path, movieFromApi.vote_average, movieFromApi.release_date)
            }
        }
    }

    override fun getUpcomingMovieList(): Single<List<Movie>> {
        return dataSource.getUpcomingMovieList().map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(movieFromApi.id, movieFromApi.title, movieFromApi.poster_path, movieFromApi.vote_average, movieFromApi.release_date)
            }
        }
    }

    override fun getVideo(id: Int): Single<ResponseVideo> {
        return dataSource.getVideo(id)
    }

    override fun getAllLists(): Single<List<CategoryWithListItem>> {
        return Single.zip(getNowPlayingMovieList(),
            getPopularMovieList(),
            getTopRatedMovieList(),
            getUpcomingMovieList()) { NowPlaying, Popular, TopRated, Upcoming ->
            listOf(
                CategoryWithListItem(CATEGORY_TITLE_NOW_PLAYING,
                    CATEGORY_DESCRIPTION_NOW_PLAYING,
                    NowPlaying),
                CategoryWithListItem(CATEGORY_TITLE_POPULAR, CATEGORY_DESCRIPTION_POPULAR, Popular),
                CategoryWithListItem(CATEGORY_TITLE_TOP_RATED,
                    CATEGORY_DESCRIPTION_TOP_RATED,
                    TopRated),
                CategoryWithListItem(CATEGORY_TITLE_UPCOMING,
                    CATEGORY_DESCRIPTION_UPCOMING,
                    Upcoming),
            )
        }
    }

    override fun getListOfGenres(): Single<ListOfGenres> {
        return dataSource.getListOfGenres()
    }

    override fun getListMovieWithGenre(page: Int?, id: Int): Single<List<Movie>> {
        return dataSource.getListMovieWithGenre(page, id).map { responseResult ->
            responseResult.results.map { movieWithSpecificGenres ->
                Movie(movieWithSpecificGenres.id,
                    movieWithSpecificGenres.title,
                    movieWithSpecificGenres.posterPath,
                movieWithSpecificGenres.voteAverage,
                movieWithSpecificGenres.releaseDate)
            }
        }
    }

    override fun getPopularMovieList(page: Int?): Single<List<Movie>> {
        return dataSource.getPopularMovieList(page).map { responseResult ->
            responseResult.results.map { movieFromApi ->
                Movie(movieFromApi.id, movieFromApi.title, movieFromApi.poster_path, movieFromApi.vote_average, movieFromApi.release_date)
            }
        }
    }

    override fun getDetailMovie(id: Int): Single<MovieDetails> {
        val movieDetailsResponse = dataSource.getDetailMovie(id)
        val crew = dataSource.getCrewCinema(id)
        val favoriteMovies = localDataSource.getAllMovies()

        return Single.zip(
            movieDetailsResponse,
            crew,
            favoriteMovies) { movieDetails, crew, favoriteMovies ->
            val isInFavorite = favoriteMovies.find { it.movieId == id } != null
            mapper.map(MapperResponsesToMovieDetails.Responses(movieDetails, crew, isInFavorite))
        }
    }

    companion object {
        private const val CATEGORY_TITLE_NOW_PLAYING = "NowPlaying"
        private const val CATEGORY_TITLE_POPULAR = "Popular"
        private const val CATEGORY_TITLE_TOP_RATED = "TopRated"
        private const val CATEGORY_TITLE_UPCOMING = "Upcoming"

        private const val CATEGORY_DESCRIPTION_NOW_PLAYING = "Actual films of the world of cinema"
        private const val CATEGORY_DESCRIPTION_POPULAR = "Popular at all times"
        private const val CATEGORY_DESCRIPTION_TOP_RATED = "Highest ratings from a viewer"
        private const val CATEGORY_DESCRIPTION_UPCOMING = "Coming soon on all screens"
    }


}