package com.metflix.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.metflix.data.mapper.ActorMapper
import com.metflix.data.mapper.MovieMapper
import com.metflix.data.source.RemoteDataSource
import com.metflix.domain.entity.Actor
import com.metflix.domain.entity.Movie
import com.metflix.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val movieMapper: MovieMapper,
    private val actorMapper: ActorMapper
) : AppRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return remoteDataSource.getPopularMovies().map { it.map(movieMapper::map) }
    }

    override fun getMovieDetails(movieId: Int): LiveData<Movie> {
        return remoteDataSource.getMovieDetails(movieId).map(movieMapper::map)
    }

    override fun getActors(movieId: Int): LiveData<List<Actor>> {
        return remoteDataSource.getActors(movieId).map { it.map(actorMapper::map) }
    }
}