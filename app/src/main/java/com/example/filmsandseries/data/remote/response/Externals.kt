package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Externals(
    @SerializedName("tvrage") val tvrage : Int? = null,
    @SerializedName("thetvdb") val thetvdb : Int? = null,
    @SerializedName("imdb") val imdb : String? = null
)