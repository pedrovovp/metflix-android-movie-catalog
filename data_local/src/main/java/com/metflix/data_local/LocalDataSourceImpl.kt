package com.metflix.data_local

import com.metflix.data.model.MovieData
import com.metflix.data.source.LocalDataSource
import com.metflix.data_local.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalDataSourceImpl(database: AppDatabase) : LocalDataSource {
    private val getDao = database.movieDao()

    override fun loadMovies(): Flow<List<MovieData>> {
        return getDao.getMovies().map{it.map(MovieConverter::fromEntity)}
    }

    override suspend fun saveMovie(movie: MovieData) {
        if(movie.id == 0) {
            movie.id = Math.random().toInt()
        }
        getDao.save(MovieConverter.fromData(movie))
    }

    override suspend fun removeMovie(movie: MovieData) {
        getDao.delete(MovieConverter.fromData(movie))
    }
}