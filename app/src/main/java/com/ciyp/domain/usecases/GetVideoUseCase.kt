package com.ciyp.domain.usecases

import com.ciyp.datalayer.remote.dto.video.ResponseVideo
import com.ciyp.domain.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getVideo(id: Int): Single<ResponseVideo> {
        return repository.getVideo(id)
    }

}