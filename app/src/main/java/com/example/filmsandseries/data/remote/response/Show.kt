package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("language") val language: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("status") val status: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("averageRuntime") val averageRuntime: Int,
    @SerializedName("premiered") val premiered: String,
    @SerializedName("ended") val ended: String,
    @SerializedName("officialSite") val officialSite: String,
    @SerializedName("schedule") val schedule: Schedule,
    @SerializedName("rating") val rating: Rating,
    @SerializedName("weight") val weight: Int,
    @SerializedName("network") val network: Network,
    @SerializedName("externals") val externals: Externals,
    @SerializedName("image") val image: Image,
    @SerializedName("summary") val summary: String,
    @SerializedName("updated") val updated: Int,
    @SerializedName("links") val links: Links
)
