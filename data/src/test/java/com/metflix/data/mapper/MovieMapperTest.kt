package com.metflix.data.mapper

import com.metflix.data.factory.DataFactory
import com.metflix.data.model.MovieData
import com.metflix.domain.entity.Movie
import org.junit.Test
import kotlin.test.assertEquals

class MovieMapperTest {

    private val mapper: Mapper<MovieData, Movie> = MovieMapper()

    @Test
    fun testMovieDomainEqualsData() {
        // Given
        val expected = DataFactory.dummyMovie()
        val movie = DataFactory.dummyMovieData()

        // When
        val result = mapper.map(movie)

        // Then
        assertEquals(expected, result)
    }
}