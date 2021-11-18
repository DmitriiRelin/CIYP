package com.ciyp.datalayer.remote.dto.genres.listOfSpecificGenres

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListOfSpecificGenres(
    @SerializedName("page")
    @Expose
    var page: Int = 0,

    @SerializedName("results")
    @Expose
    var results: List<MovieWithSpecificGenres>,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0,
)