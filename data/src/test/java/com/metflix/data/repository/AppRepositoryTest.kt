package com.metflix.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.metflix.data.factory.DataFactory
import com.metflix.data.mapper.ActorMapper
import com.metflix.data.mapper.MovieDataMapper
import com.metflix.data.mapper.MovieMapper
import com.metflix.data.source.LocalDataSource
import com.metflix.data.source.RemoteDataSource
import com.metflix.domain.repository.AppRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

internal class AppRepositoryTest {

    private lateinit var repository: AppRepository
    private val localDataSource = mockk<LocalDataSource>()
    private val remoteDataSource = mockk<RemoteDataSource>()

    @Before
    fun setup() {
        repository = AppRepositoryImpl(
        remoteDataSource,
        localDataSource,
        MovieDataMapper(),
        MovieMapper(),
        ActorMapper())
    }

    @Test
    fun testListPopularMovies() {
        // Given
        every { remoteDataSource.listPopularMovies() } returns flowOf(PagingData.from(DataFactory.dummyMovieDataList()))

        // When
        repository.listPopularMovies()

        //Then
        verify { remoteDataSource.listPopularMovies() }
    }

    @Test
    fun testGetMovieDetails() {
        // Given
        val id = 1
        every { remoteDataSource.getMovieDetails(id) } returns MutableLiveData(DataFactory.dummyMovieData())

        // When
        repository.getMovieDetails(id)

        // Then
        verify { remoteDataSource.getMovieDetails(id) }
    }

    @Test
    fun testGetActors() {
        // Given
        val id = 1
        every { remoteDataSource.getActors(id) } returns MutableLiveData(DataFactory.dummyActorDataList())

        // When
        repository.getActors(id)

        // Then
        verify { remoteDataSource.getActors(id) }
    }

    @Test
    fun testGetSavedMovies() {
        // Given
        every { localDataSource.loadMovies() } returns flowOf(DataFactory.dummyMovieDataList())

        // When
        repository.getSavedMovies()

        // Then
        verify { localDataSource.loadMovies() }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveMovie() = runBlockingTest {
        // Given
        val movie = DataFactory.dummyMovie()
        val movieData = MovieDataMapper().map(movie)
        coEvery { localDataSource.saveMovie(movieData) } just Runs

        // When
        repository.saveMovie(movie)

        // Then
        coVerify { localDataSource.saveMovie(movieData) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testRemoveMovie() = runBlockingTest {
        // Given
        val movie = DataFactory.dummyMovie()
        val movieData = MovieDataMapper().map(movie)
        coEvery { localDataSource.removeMovie(movieData) } just Runs

        // When
        repository.removeMovie(movie)

        // Then
        coVerify { localDataSource.removeMovie(movieData) }
    }
}