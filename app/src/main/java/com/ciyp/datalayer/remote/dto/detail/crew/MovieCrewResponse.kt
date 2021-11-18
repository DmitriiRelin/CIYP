package com.ciyp.datalayer.remote.dto.detail.crew

import com.google.gson.annotations.SerializedName

data class MovieCrewResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("cast") val castFromApi : List<CastFromApi>,
)