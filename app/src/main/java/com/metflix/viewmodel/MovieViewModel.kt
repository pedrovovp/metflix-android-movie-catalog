package com.metflix.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.metflix.data.AppRepository
import com.metflix.data.domain.Movie
import com.metflix.utils.ResultData

class MovieViewModel(private val repository: AppRepository) : ViewModel(), LifecycleObserver {

    fun getMovieDetails(movieId: Int): LiveData<ResultData<Movie?>> {
        return repository.getMovieDetails(movieId)
    }
}