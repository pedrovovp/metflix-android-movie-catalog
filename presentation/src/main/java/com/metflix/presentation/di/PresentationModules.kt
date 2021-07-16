package com.metflix.presentation.di

import com.metflix.presentation.MovieListViewModel
import com.metflix.presentation.MovieViewModel
import com.metflix.presentation.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel {
        MovieListViewModel(get(), get(), get(), get())
    }

    viewModel {
        MovieViewModel(get(), get(), get())
    }

    viewModel {
        WatchlistViewModel(get(),get(), get())
    }
}

