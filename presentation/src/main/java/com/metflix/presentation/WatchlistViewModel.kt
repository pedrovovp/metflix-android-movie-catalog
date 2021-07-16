package com.metflix.presentation

import androidx.lifecycle.*
import com.metflix.domain.entity.Movie
import com.metflix.domain.interactor.ListSavedMoviesUseCase
import com.metflix.domain.interactor.SaveMovieUseCase
import com.metflix.domain.interactor.RemoveMovieUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class WatchlistViewModel(
    private val listSavedMoviesUseCase: ListSavedMoviesUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase
    ) : ViewModel(), LifecycleObserver {

    var state = MutableLiveData<ViewState<List<Movie>>>()
    private set

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun listSavedMovies() {
        if(state.value == null) {
            viewModelScope.launch {
                state.postValue(ViewState(ViewState.Status.LOADING))
                try {
                    listSavedMoviesUseCase.execute()
                        .catch { e -> state.postValue(ViewState(ViewState.Status.ERROR, error = e)) }
                        .collect {
                            state.postValue(ViewState(ViewState.Status.SUCCESS, data = it))
                        }
                } catch (e: Exception) {
                    state.postValue(ViewState(ViewState.Status.ERROR, error = e))
                }
            }
        }
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