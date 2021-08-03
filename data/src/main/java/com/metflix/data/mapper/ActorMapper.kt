package com.metflix.data.mapper

import com.metflix.data.model.ActorsData
import com.metflix.domain.entity.Actor

internal class ActorMapper: Mapper<ActorsData.Actor, Actor>{
    override fun map(source: ActorsData.Actor): Actor {
        return Actor(
            id = source.id,
            name = source.name,
            character = source.character,
            profilePath = source.profile_path
        )
    }
}