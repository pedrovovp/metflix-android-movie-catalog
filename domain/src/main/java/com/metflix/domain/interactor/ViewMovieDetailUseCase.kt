package com.metflix.domain.interactor

import androidx.lifecycle.LiveData
import com.metflix.domain.entity.Movie
import com.metflix.domain.repository.AppRepository

open class ViewMovieDetailUseCase(private val repository: AppRepository) {

    fun execute(movieId: Int): LiveData<Movie> = repository.getMovieDetails(movieId)
}