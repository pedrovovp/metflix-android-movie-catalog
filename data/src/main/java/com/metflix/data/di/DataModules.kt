package com.metflix.data.di

import com.metflix.data.mapper.ActorMapper
import com.metflix.data.mapper.MovieDataMapper
import com.metflix.data.mapper.MovieMapper
import com.metflix.data.repository.AppRepositoryImpl
import com.metflix.domain.repository.AppRepository
import org.koin.dsl.module


val dataModules = module {
    factory<AppRepository> {
        AppRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            movieDataMapper = MovieDataMapper(),
            movieMapper = MovieMapper(),
            actorMapper = ActorMapper()
        )
    }
}