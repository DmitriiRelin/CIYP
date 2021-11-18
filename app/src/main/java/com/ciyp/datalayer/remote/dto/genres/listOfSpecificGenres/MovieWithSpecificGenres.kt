package com.ciyp.datalayer.remote.dto.genres.listOfSpecificGenres

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieWithSpecificGenres(
    @SerializedName("adult")
    @Expose
    var isAdult: Boolean = false,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,

    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.0,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("video")
    @Expose
    var isVideo: Boolean = false,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int = 0,
)