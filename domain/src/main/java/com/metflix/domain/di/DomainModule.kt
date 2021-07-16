package com.metflix.domain.di

import com.metflix.domain.interactor.*
import org.koin.dsl.module

val domainModules = module {
    factory { ListPopularMoviesUseCase(repository = get()) }
    factory { ListActorsUseCase(repository = get()) }
    factory { ViewMovieDetailUseCase(repository = get()) }
    factory { ListSavedMoviesUseCase(repository = get()) }
    factory { SaveMovieUseCase(repository = get()) }
    factory { RemoveMovieUseCase(repository = get()) }
}