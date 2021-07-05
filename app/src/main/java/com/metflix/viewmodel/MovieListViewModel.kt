package com.metflix.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metflix.data.AppRepository
import com.metflix.data.domain.Movie
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: AppRepository) : ViewModel(), LifecycleObserver {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies().cachedIn(viewModelScope)
    }
}