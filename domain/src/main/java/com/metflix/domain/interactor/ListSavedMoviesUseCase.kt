package com.metflix.domain.interactor

import androidx.paging.PagingData
import com.metflix.domain.entity.Movie
import com.metflix.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

open class ListSavedMoviesUseCase(private val repository: AppRepository) {

    fun execute(): Flow<List<Movie>> = repository.getSavedMovies()
}