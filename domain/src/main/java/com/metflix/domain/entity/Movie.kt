package com.metflix.domain.entity

data class Movie(
    var id: Int = 0,
    var title: String = "",
    var overview: String? = "",
    var releaseDate: String? = "",
    var genres: List<Genre>? = ArrayList(),
    var posterPath: String? = "",
    var popularity: Double? = 0.0,
    var voteCount: Int? = 0,
    var voteAverage: Double? = 0.0,
    var backdropPath: String? = "",
    var runtime: String? = ""
) {
    data class Genre(
        var id: Int,
        var name: String
    )

    fun formatRuntime(): String {
        runtime?.toInt()?.let {
            val hours = it.div(60);
            val minutes = it.mod(60);
            return "$hours" + "h " + "$minutes" + "m"
        } ?: let {
            return "";
        }
    }

    fun genresToString(): String {
        var result = ""

        genres?.forEachIndexed { id, genre ->
            result += if (id != genres!!.lastIndex) "${genre.name}, " else genre.name
        }
        return result
    }
}
