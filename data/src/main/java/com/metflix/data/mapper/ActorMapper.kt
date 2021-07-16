package com.metflix.data.mapper

import com.metflix.data.model.ActorsData
import com.metflix.domain.entity.Actor

object ActorMapper{
    fun fromData(source: ActorsData.Actor): Actor {
        return Actor(
            id = source.id,
            name = source.name,
            character = source.character,
            profilePath = source.profile_path
        )
    }
}