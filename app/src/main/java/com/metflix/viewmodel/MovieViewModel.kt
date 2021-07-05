package com.metflix.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.metflix.data.repository.AppRepository
import com.metflix.data.model.Movie
import com.metflix.utils.ResultData

class MovieViewModel(private val repository: AppRepository) : ViewModel(), LifecycleObserver {

    fun getMovieDetails(movieId: Int): LiveData<ResultData<Movie?>> {
        return repository.getMovieDetails(movieId)
    }
}