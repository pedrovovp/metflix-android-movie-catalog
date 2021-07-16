package com.metflix.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metflix.domain.entity.Actor
import com.metflix.domain.entity.Movie
import com.metflix.domain.interactor.RemoveMovieUseCase
import com.metflix.domain.interactor.SaveMovieUseCase
import com.metflix.domain.repository.AppRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: AppRepository,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase
) : ViewModel(), LifecycleObserver {

    fun getMovieDetails(movieId: Int): LiveData<Movie> {
        return repository.getMovieDetails(movieId)
    }

    fun getActors(movieId: Int): LiveData<List<Actor>> {
        return repository.getActors(movieId)
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            saveMovieUseCase.execute(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        viewModelScope.launch {
            removeMovieUseCase.execute(movie)
        }
    }
}