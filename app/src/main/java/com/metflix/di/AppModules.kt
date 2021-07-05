package com.metflix.di

import com.metflix.data.AppRepository
import com.metflix.data.AppRepositoryImpl
import com.metflix.data.api.AppInterceptor
import com.metflix.data.api.AppService
import com.metflix.data.api.AppService.Companion.SERVER_HOST
import com.metflix.data.paging.MoviePagingSource
import com.metflix.viewmodel.MovieListViewModel
import com.metflix.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModules = module {
    viewModel {
        MovieListViewModel(get())
    }

    viewModel {
        MovieViewModel(get())
    }
}

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(SERVER_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AppInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }

    single { get<Retrofit>().create(AppService::class.java) }
}

val repositoryModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(get())
    }
    factory {
        MoviePagingSource(get())
    }
}

val appModules = viewModelModules + apiModule + repositoryModule