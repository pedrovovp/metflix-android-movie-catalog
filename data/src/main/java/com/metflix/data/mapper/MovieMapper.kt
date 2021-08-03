package com.metflix.data.mapper

import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie

internal class MovieMapper: Mapper<MovieData, Movie> {
    override fun map(source: MovieData): Movie {
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

    private fun mapGenre(genre: MovieData.Genre): Movie.Genre {
        return Movie.Genre(
            id = genre.id,
            name = genre.name
        )
    }
}