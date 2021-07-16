package com.metflix.data.model

data class ActorsData(
    val id: Int,
    val cast: List<Actor>
) {
    data class Actor(
        val id: Int,
        val name: String,
        val character: String,
        val profile_path: String?
    )
}