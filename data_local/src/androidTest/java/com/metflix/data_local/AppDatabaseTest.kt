package com.metflix.data_local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.metflix.data_local.dao.MovieDao
import com.metflix.data_local.database.AppDatabase
import com.metflix.data_local.entity.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class AppDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java).allowMainThreadQueries().build()
        dao = database.movieDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun saveMovie() = runBlocking {
        // Given
        val dummyMovie = Movie(id = 1, posterPath = "", title = "Avengers", voteAverage = 9.0)

        // When
        dao.save(dummyMovie)

        val list =  dao.getMovies().first()

        // Then
        assert(list.contains(dummyMovie))
    }

    @Test
    fun deleteMovie() = runBlocking {
        // Given
        val dummyMovie = Movie(id = 1, posterPath = "", title = "Avengers", voteAverage = 9.0)

        // When
        dao.save(dummyMovie)
        dao.delete(dummyMovie)

        val list =  dao.getMovies().first()

        // Then
        assert(!list.contains(dummyMovie))
    }
}