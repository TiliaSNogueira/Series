package com.example.filmsandseries.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "show")
data class ShowDetails(
    @PrimaryKey
    var id: Int? = null,
    var image: String? = null,
    var title: String? = null,
    var summary: String? = null,
    var genres: List<String?>? = null,
    var rating: Double? = null
) : java.io.Serializable
