package com.ciyp.datalayer.remote.dto.genres.listOfGenres

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListOfGenres {
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null
}