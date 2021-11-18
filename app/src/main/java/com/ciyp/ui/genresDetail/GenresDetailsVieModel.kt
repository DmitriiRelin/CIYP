package com.ciyp.ui.genresDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.ciyp.domain.entites.Movie
import com.ciyp.domain.usecases.GetMovieListWithGenreUseCase
import com.ciyp.ui.genresDetail.genresDetailsRecyclerView.MovieWithGenresSource
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GenresDetailsVieModel @Inject constructor(
    private val getMovieListWithGenreUseCase: GetMovieListWithGenreUseCase,

    ) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_PAGE_SIZE = 30
        private const val PREFETCH_DISTANCE = 5
        private const val INITIAL_LOAD_SIZE = 40
    }

    fun getMovies(id: Int): Flowable<PagingData<Movie>> {
        val movieSource = MovieWithGenresSource(getMovieListWithGenreUseCase, id)
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                maxSize = MAX_PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE),
            pagingSourceFactory = { movieSource }
        ).flowable
    }


}


@Suppress("UNCHECKED_CAST")
class GenresDetailsVieModelFactory @Inject constructor(private val getMovieListWithGenreUseCase: GetMovieListWithGenreUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetMovieListWithGenreUseCase::class.java)
            .newInstance(getMovieListWithGenreUseCase)
    }
}


