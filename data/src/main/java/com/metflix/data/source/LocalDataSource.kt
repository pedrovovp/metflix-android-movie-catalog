package com.metflix.data.source

import com.metflix.data.model.MovieData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun loadMovies(): Flow<List<MovieData>>
    suspend fun saveMovie(movie: MovieData)
    suspend fun removeMovie(movie: MovieData)
}