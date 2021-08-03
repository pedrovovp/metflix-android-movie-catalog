package com.metflix.data.mapper

import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie

internal class MovieDataMapper: Mapper<Movie, MovieData> {
    override fun map(source: Movie): MovieData {
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
}