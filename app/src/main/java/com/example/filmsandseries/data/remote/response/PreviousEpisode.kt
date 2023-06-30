package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class PreviousEpisode(
    @SerializedName("href") val href : String? = null
)