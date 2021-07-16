package com.metflix.data_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class Movie(
    @PrimaryKey
    var id: Int = 0,
    var posterPath: String? = "",
    var title: String = "",
    var voteAverage: Double? = 0.0,
)