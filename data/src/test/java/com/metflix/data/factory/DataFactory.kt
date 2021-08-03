package com.metflix.data.factory

import com.metflix.data.model.ActorsData
import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie

object DataFactory {

    fun dummyMovieDataList() = listOf(
        MovieData().apply {
            id = 1
            title = "Avengers"
            overview = "A super hero movie!"
            release_date = "12/12/2012"
            genres = listOf(
                MovieData.Genre(id = 1, name = "Action"),
                MovieData.Genre(id = 2, name = "Adventure")
            )
            poster_path = ""
            popularity = 5.0
            vote_count = 7
            vote_average = 8.5
            backdrop_path = ""
            runtime = "120"
        },

        MovieData().apply {
            id = 2
            title = "Lord of The Rings"
            overview = "Frodo has quest: go to Mordor and destroy the ring"
            release_date = "01/01/2001"
            genres = listOf(
                MovieData.Genre(id = 1, name = "Fantasy"),
                MovieData.Genre(id = 2, name = "Adventure")
            )
            poster_path = ""
            popularity = 10.0
            vote_count = 9
            vote_average = 9.5
            backdrop_path = ""
            runtime = "180"
        }
    )

    fun dummyMovieData() = MovieData().apply {
        id = 1
        title = "Avengers"
        overview = "A super hero movie!"
        release_date = "12/12/2012"
        genres = listOf(
            MovieData.Genre(id = 1, name = "Action"),
            MovieData.Genre(id = 2, name = "Adventure")
        )
        poster_path = ""
        popularity = 5.0
        vote_count = 7
        vote_average = 8.5
        backdrop_path = ""
        runtime = "120"
    }

    fun dummyMovieList() = listOf(
        Movie().apply {
            id = 1
            title = "Avengers"
            overview = "A super hero movie!"
            releaseDate = "12/12/2012"
            genres = listOf(
                Movie.Genre(id = 1, name = "Action"),
                Movie.Genre(id = 2, name = "Adventure")
            )
            posterPath = ""
            popularity = 5.0
            voteCount = 7
            voteAverage = 8.5
            backdropPath = ""
            runtime = "120"
        },

        Movie().apply {
            id = 2
            title = "Lord of The Rings"
            overview = "Frodo has quest: go to Mordor and destroy the ring"
            releaseDate = "01/01/2001"
            genres = listOf(
                Movie.Genre(id = 1, name = "Fantasy"),
                Movie.Genre(id = 2, name = "Adventure")
            )
            posterPath = ""
            popularity = 10.0
            voteCount = 9
            voteAverage = 9.5
            backdropPath = ""
            runtime = "180"
        }
    )

    fun dummyMovie() = Movie().apply {
        id = 1
        title = "Avengers"
        overview = "A super hero movie!"
        releaseDate = "12/12/2012"
        genres = listOf(
            Movie.Genre(id = 1, name = "Action"),
            Movie.Genre(id = 2, name = "Adventure")
        )
        posterPath = ""
        popularity = 5.0
        voteCount = 7
        voteAverage = 8.5
        backdropPath = ""
        runtime = "120"
    }

    fun dummyActorDataList() = listOf(
        ActorsData.Actor(
            id = 1,
            name = "Chris Evans",
            character = "Captain America",
            profile_path = ""
        ),
        ActorsData.Actor(
            id = 2,
            name = "Robert Downey Jr.",
            character = "Iron Man",
            profile_path = ""
        )
    )
}