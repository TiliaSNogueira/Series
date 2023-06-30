package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self") val self: Self? = null,
    @SerializedName("previousepisode") val previousepisode: PreviousEpisode? = null
)