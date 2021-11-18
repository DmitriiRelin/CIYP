package com.ciyp.domain.usecases

import com.ciyp.domain.MovieRepository
import com.ciyp.ui.home.homeRecyclerView.base.CategoryWithListItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAllListsUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getAllLists(): Single<List<CategoryWithListItem>> {
        return repository.getAllLists()
    }

}