package com.ciyp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ciyp.domain.usecases.GetAllListsUseCase
import com.ciyp.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllListsUseCase: GetAllListsUseCase) :
    ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _listsMoviesLiveData = MutableLiveData<List<CategoryWithListItem>>()
    val listsMoviesLiveData: LiveData<List<CategoryWithListItem>> = _listsMoviesLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = _errorLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    fun getHomeList() {
        _isLoadingLiveData.value = true
        getAllListsUseCase.getAllLists().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _isLoadingLiveData.value = false
                _listsMoviesLiveData.value = it
            }, {
                _isLoadingLiveData.value = false
                _errorLiveData.value = it
            }).also {
                disposables.add(it)
            }
    }

    override fun onCleared() {
        disposables.clear()
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory @Inject constructor(private val getAllListsUseCase: GetAllListsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetAllListsUseCase::class.java)
            .newInstance(getAllListsUseCase)
    }
}
