package com.metflix.data.mapper

import com.metflix.data.model.ActorsResponse
import com.metflix.data.model.MovieResponse
import com.metflix.domain.entity.Actor

class ActorMapper: Mapper<ActorsResponse.Actor, Actor> {
    override fun map(source: ActorsResponse.Actor): Actor {
        return Actor(
            id = source.id,
            name = source.name,
            character = source.character,
            profilePath = source.profile_path
        )
    }
}