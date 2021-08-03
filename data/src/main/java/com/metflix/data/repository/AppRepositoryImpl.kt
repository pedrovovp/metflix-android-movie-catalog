package com.metflix.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.metflix.data.mapper.Mapper
import com.metflix.data.model.ActorsData
import com.metflix.data.model.MovieData
import com.metflix.data.source.LocalDataSource
import com.metflix.data.source.RemoteDataSource
import com.metflix.domain.entity.Actor
import com.metflix.domain.entity.Movie
import com.metflix.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDataMapper: Mapper<Movie, MovieData>,
    private val movieMapper: Mapper<MovieData, Movie>,
    private val actorMapper: Mapper<ActorsData.Actor, Actor>
) : AppRepository {

    override fun listPopularMovies(): Flow<PagingData<Movie>> {
        return remoteDataSource.listPopularMovies().map { it.map(movieMapper::map) }
    }

    override fun getMovieDetails(movieId: Int): LiveData<Movie> {
        return remoteDataSource.getMovieDetails(movieId).map(movieMapper::map)
    }

    override fun getActors(movieId: Int): LiveData<List<Actor>> {
        return remoteDataSource.getActors(movieId).map { it.map(actorMapper::map) }
    }

    override fun getSavedMovies(): Flow<List<Movie>> {
        return localDataSource.loadMovies().map { it.map(movieMapper::map) }
    }

    override suspend fun saveMovie(movie: Movie) {
        localDataSource.saveMovie(movieDataMapper.map(movie))
    }

    override suspend fun removeMovie(movie: Movie) {
        localDataSource.removeMovie(movieDataMapper.map(movie))
    }
}