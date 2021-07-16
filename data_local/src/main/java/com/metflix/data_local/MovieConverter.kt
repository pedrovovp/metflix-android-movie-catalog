package com.metflix.data_local

import com.metflix.data.model.MovieData
import com.metflix.data_local.entity.Movie as MovieEntity

internal object MovieConverter {

    fun fromData(data: MovieData) = MovieEntity().apply {
        id = data.id
        posterPath = data.poster_path
        title = data.title
        voteAverage = data.vote_average
    }

    fun fromEntity(entity: MovieEntity) = MovieData(
        id = entity.id,
        poster_path = entity.posterPath,
        title = entity.title,
        vote_average = entity.voteAverage
    )
}