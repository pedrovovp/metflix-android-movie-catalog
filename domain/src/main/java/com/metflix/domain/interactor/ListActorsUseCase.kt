package com.metflix.domain.interactor

import androidx.lifecycle.LiveData
import com.metflix.domain.entity.Actor
import com.metflix.domain.repository.AppRepository

open class ListActorsUseCase(private val repository: AppRepository) {

    fun execute(movieId: Int): LiveData<List<Actor>> = repository.getActors(movieId)
}