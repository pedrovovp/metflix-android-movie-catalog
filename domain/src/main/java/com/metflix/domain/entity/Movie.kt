package com.metflix.domain.entity

data class Movie(
    val overview: String?,
    val releaseDate: String?,
    val genres: List<Genre>?,
    val posterPath: String?,
    val id: Int,
    val title: String,
    val popularity: Double?,
    val voteCount: Int?,
    val voteAverage: Double?,
    val backdropPath: String?,
    val runtime: String?
) {
    data class Genre(
        val id: Int,
        val name: String
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
            result += if (id != genres.lastIndex) "${genre.name}, " else genre.name
        }
        return result
    }
}
