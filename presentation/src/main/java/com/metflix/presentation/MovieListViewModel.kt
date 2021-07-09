package com.metflix.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metflix.domain.repository.AppRepository
import com.metflix.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: AppRepository) : ViewModel(), LifecycleObserver {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies().cachedIn(viewModelScope)
    }
}