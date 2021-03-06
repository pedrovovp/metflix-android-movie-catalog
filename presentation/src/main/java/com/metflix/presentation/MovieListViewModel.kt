package com.metflix.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.metflix.domain.entity.Movie
import com.metflix.domain.interactor.ListSavedMoviesUseCase
import com.metflix.domain.interactor.RemoveMovieUseCase
import com.metflix.domain.interactor.SaveMovieUseCase
import com.metflix.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListViewModel(
    private val repository: AppRepository,
    private val listSavedMoviesUseCase: ListSavedMoviesUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase
) : ViewModel(), LifecycleObserver {
    private val savedMovies = MutableLiveData<ViewState<List<Movie>>>()
    fun savedMovies() = savedMovies

    fun listSavedMovies() {
        if(savedMovies.value == null) {
            viewModelScope.launch {
                savedMovies.postValue(ViewState(ViewState.Status.LOADING))
                try {
                    listSavedMoviesUseCase.execute()
                        .catch { e -> savedMovies.postValue(ViewState(ViewState.Status.ERROR, error = e)) }
                        .collect {
                            savedMovies.postValue(ViewState(ViewState.Status.SUCCESS, data = it))
                        }
                } catch (e: Exception) {
                    savedMovies.postValue(ViewState(ViewState.Status.ERROR, error = e))
                }
            }
        }
    }

    fun listPopularMovies(): Flow<PagingData<Movie>> {
        return repository.listPopularMovies().cachedIn(viewModelScope)
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