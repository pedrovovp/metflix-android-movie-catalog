package com.metflix.data_remote.di

import com.metflix.data.source.RemoteDataSource
import com.metflix.data_remote.AppInterceptor
import com.metflix.data_remote.AppService
import com.metflix.data_remote.AppService.Companion.SERVER_HOST
import com.metflix.data_remote.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
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

    factory<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }
}