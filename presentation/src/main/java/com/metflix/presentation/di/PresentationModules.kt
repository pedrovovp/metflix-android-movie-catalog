package com.metflix.presentation.di

import com.metflix.presentation.MovieListViewModel
import com.metflix.presentation.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel {
        MovieListViewModel(get())
    }

    viewModel {
        MovieViewModel(get())
    }
}

