package com.metflix.data.mapper

import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie

object MovieMapper {
    fun fromData(source: MovieData): Movie {
        return Movie(
            overview = source.overview,
            releaseDate = source.release_date,
            genres = source.genres?.map { genre -> mapGenre(genre) },
            posterPath = source.poster_path,
            id = source.id,
            title = source.title,
            popularity = source.popularity,
            voteCount = source.vote_count,
            voteAverage = source.vote_average,
            backdropPath = source.backdrop_path,
            runtime = source.runtime
        )
    }

    fun fromEntity(source: Movie): MovieData {
        return MovieData(
            overview = source.overview,
            release_date = source.releaseDate,
            poster_path = source.posterPath,
            id = source.id,
            title = source.title,
            popularity = source.popularity,
            vote_count = source.voteCount,
            vote_average = source.voteAverage,
            backdrop_path = source.backdropPath,
            runtime = source.runtime
        )
    }

    private fun mapGenre(genre: MovieData.Genre): Movie.Genre {
        return Movie.Genre(
            id = genre.id,
            name = genre.name
        )
    }
}