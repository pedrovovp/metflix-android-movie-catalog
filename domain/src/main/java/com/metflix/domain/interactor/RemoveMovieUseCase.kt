package com.metflix.domain.interactor

import com.metflix.domain.entity.Movie
import com.metflix.domain.repository.AppRepository

open class RemoveMovieUseCase(private val repository: AppRepository) {

    suspend fun execute(movie: Movie) = repository.removeMovie(movie)
}