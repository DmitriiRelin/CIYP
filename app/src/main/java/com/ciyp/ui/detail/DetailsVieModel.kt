package com.ciyp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.domain.usecases.AddFavoriteMovieUseCase
import com.ciyp.domain.usecases.DeleteFavoriteMovieUseCase
import com.ciyp.domain.usecases.GetDetailMovieUseCase
import com.ciyp.domain.usecases.GetVideoUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailsVieModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val getVideoUseCase: GetVideoUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : ViewModel() {

    private var disposable: Disposable? = null
    private var disposable2: Disposable? = null

    private val _movieLiveData = MutableLiveData<MovieDetails>()
    val movieLiveData: LiveData<MovieDetails> = _movieLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    private val _errorVideoLiveData = MutableLiveData<String>()
    val errorVideoLiveData: LiveData<String> = _errorVideoLiveData

    private val _videoLiveData = MutableLiveData<List<String>>()
    val videoLiveData: LiveData<List<String>> = _videoLiveData

    fun getVideo(id: Int?) {
        id?.let {
            disposable2 = getVideoUseCase.getVideo(id).subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ video ->
                    if (video.results.isNotEmpty()) {
                        //val url = "https://www.youtube.com/watch?v=${video.results[0].key}"
                        val url = video.results[0].key
                        _videoLiveData.value = video.results.map { it.key }
                    }
                    _errorVideoLiveData.value = "No Video"
                }, {
                    _errorVideoLiveData.value = "Error Video"
                })
        }
    }

    fun getMovieDetail(id: Int?) {
        id?.let {
            disposable = getDetailMovieUseCase.getDetailMovie(id).subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ movieDetails ->
                    _movieLiveData.value = movieDetails
                },
                    {
                        _errorLiveData.value = it
                        Log.d("error", it.message.toString())
                    })
        }
    }

    fun changeFavoriteStatus() {
        _movieLiveData.value?.let {
            if (it.isInFavorite == false) {
                addFavoriteMovieUseCase.addToFavorite(it).subscribeOn(Schedulers.io()).subscribe()
            } else {
                deleteFavoriteMovieUseCase.deleteMovie(it).subscribeOn(Schedulers.io()).subscribe()
            }
            getMovieDetail(it.id)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val getVideoUseCase: GetVideoUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetDetailMovieUseCase::class.java,
            AddFavoriteMovieUseCase::class.java,
            GetVideoUseCase::class.java,
            DeleteFavoriteMovieUseCase::class.java
        )
            .newInstance(
                getDetailMovieUseCase,
                addFavoriteMovieUseCase,
                getVideoUseCase,
                deleteFavoriteMovieUseCase
            )
    }
}