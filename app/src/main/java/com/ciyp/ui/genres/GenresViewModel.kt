package com.ciyp.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.Genre
import com.ciyp.domain.usecases.GetListOFGenresUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GenresViewModel @Inject constructor(
    private val getListOFGenresUseCase: GetListOFGenresUseCase,
) :
    ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _listGenresLiveData = MutableLiveData<List<Genre>>()
    val listGenresLiveData: LiveData<List<Genre>> = _listGenresLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun getListOfGenres() {
        _loadingLiveData.value = true
        getListOFGenresUseCase.getListOfGenres().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _loadingLiveData.value = false
                _listGenresLiveData.value = it.genres
            }, {
                _loadingLiveData.value = false
            }).also {
                disposables.add(it)
            }
    }

    override fun onCleared() {
        disposables.clear()
    }

}

@Suppress("UNCHECKED_CAST")
class GenresViewModelFactory @Inject constructor(private val getListOFGenresUseCase: GetListOFGenresUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetListOFGenresUseCase::class.java)
            .newInstance(getListOFGenresUseCase)
    }
}