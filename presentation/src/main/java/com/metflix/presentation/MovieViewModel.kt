package com.metflix.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.metflix.domain.entity.Actor
import com.metflix.domain.repository.AppRepository
import com.metflix.domain.entity.Movie

class MovieViewModel(private val repository: AppRepository) : ViewModel(), LifecycleObserver {

    fun getMovieDetails(movieId: Int): LiveData<Movie> {
        return repository.getMovieDetails(movieId)
    }

    fun getActors(movieId: Int): LiveData<List<Actor>> {
        return repository.getActors(movieId)
    }
}