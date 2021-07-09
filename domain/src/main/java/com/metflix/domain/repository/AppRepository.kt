package com.metflix.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.metflix.domain.entity.Actor
import com.metflix.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun getMovieDetails(movieId: Int): LiveData<Movie>

    fun getActors(movieId: Int): LiveData<List<Actor>>
}