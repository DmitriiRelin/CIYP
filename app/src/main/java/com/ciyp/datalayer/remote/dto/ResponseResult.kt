package com.ciyp.datalayer.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("total_pages")
    val total: Int = 0,
    val page: Int = 0,
    val results: List<MovieFromApi>,
)