package com.metflix.domain.di

import com.metflix.domain.interactor.ListActorsUseCase
import com.metflix.domain.interactor.ListPopularMoviesUseCase
import com.metflix.domain.interactor.ViewMovieDetailUseCase
import org.koin.dsl.module

val domainModules = module {
    factory { ListPopularMoviesUseCase(repository = get()) }
    factory { ListActorsUseCase(repository = get()) }
    factory { ViewMovieDetailUseCase(repository = get()) }
}