package com.metflix.data.model

data class MovieData(
    var id: Int = 0,
    var title: String = "",
    var overview: String? = "",
    var release_date: String? = "",
    var genres: List<Genre>? = ArrayList(),
    var poster_path: String? = "",
    var popularity: Double? = 0.0,
    var vote_count: Int? = 0,
    var vote_average: Double? = 0.0,
    var backdrop_path: String? = "",
    var runtime: String? = ""
) {
    data class Genre(
        var id: Int = 0,
        var name: String
    )
}