package com.metflix.data.domain

data class Movie(
    val overview: String,
    val release_date: String,
    val genres: List<Genre>,
    val poster_path: String,
    val id: Int,
    val title: String,
    val popularity: Double,
    val vote_count: Int,
    val vote_average: Double,
    val backdrop_path: String,
    val runtime: String?
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}
