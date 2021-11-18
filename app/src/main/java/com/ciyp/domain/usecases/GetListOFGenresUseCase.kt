package com.ciyp.domain.usecases

import com.ciyp.datalayer.remote.dto.genres.listOfGenres.ListOfGenres
import com.ciyp.domain.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetListOFGenresUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getListOfGenres(): Single<ListOfGenres> {
        return repository.getListOfGenres()
    }

}