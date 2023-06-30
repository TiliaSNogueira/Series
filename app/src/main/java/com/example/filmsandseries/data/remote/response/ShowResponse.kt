package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("genres") val genres: List<String?>? = listOf(),
    @SerializedName("status") val status: String? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("averageRuntime") val averageRuntime: Int? = null,
    @SerializedName("premiered") val premiered: String? = null,
    @SerializedName("ended") val ended: String? = null,
    @SerializedName("officialSite") val officialSite: String? = null,
    @SerializedName("schedule") val schedule: Schedule? = null,
    @SerializedName("rating") val rating: Rating? = null,
    @SerializedName("weight") val weight: Int? = null,
    @SerializedName("network") val network: Network? = null,
    @SerializedName("externals") val externals: Externals? = null,
    @SerializedName("image") val image: Image? = null,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("updated") val updated: Long? = null,
    @SerializedName("links") val links: Links? = null
)