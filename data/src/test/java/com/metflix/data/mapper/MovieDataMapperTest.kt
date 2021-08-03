package com.metflix.data.mapper

import com.metflix.data.factory.DataFactory
import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie
import org.junit.Test
import kotlin.test.assertEquals

internal class MovieDataMapperTest {

    private val mapper: Mapper<Movie, MovieData> = MovieDataMapper()

    @Test
    fun testMovieDataEqualsDomain() {
        // Given
        val expected = DataFactory.dummyMovieData()
        val movie = DataFactory.dummyMovie()

        // When
        val result = mapper.map(movie)

        // Then
        assertEquals(expected, result)
    }
}