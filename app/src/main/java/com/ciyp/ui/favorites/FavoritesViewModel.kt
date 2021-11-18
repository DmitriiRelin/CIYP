package com.ciyp.ui.favorites

import androidx.lifecycle.*
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.domain.usecases.DeleteFavoriteMovieUseCase
import com.ciyp.domain.usecases.GetAllFavoritesMoviesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesMoviesUseCase: GetAllFavoritesMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _favoritesListLiveData = MutableLiveData<List<MovieDetails>>()
    private var filterText = MutableLiveData("")
    val favoritesListLivedata: LiveData<List<MovieDetails>> =
        MediatorLiveData<List<MovieDetails>>().apply {
            addSource(_favoritesListLiveData) { value = filterMovies() }
            addSource(filterText) { value = filterMovies() }
        }

    private fun filterMovies(): List<MovieDetails> {
        return _favoritesListLiveData.value?.filter {
            it.title.contains(filterText.value ?: "",
                true)
        } ?: emptyList()
    }


    fun getAllFavoritesList() {
        getAllFavoritesMoviesUseCase.getAllFavoritesMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _favoritesListLiveData.value = it
            }, {

            }).also {
                disposables.add(it)
            }

    }

    fun deleteFavoriteMovie(movie: MovieDetails) {
        deleteFavoriteMovieUseCase.deleteMovie(movie).subscribeOn(Schedulers.io()).subscribe({

        }, {

        }).also {
            disposables.add(it)
        }
        getAllFavoritesList()
    }

    fun filter(text: String) {
        filterText.value = text
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}


@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory @Inject constructor(
    private val getAllFavoritesMoviesUseCase: GetAllFavoritesMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetAllFavoritesMoviesUseCase::class.java,
            DeleteFavoriteMovieUseCase::class.java)
            .newInstance(getAllFavoritesMoviesUseCase, deleteFavoriteMovieUseCase)
    }
}