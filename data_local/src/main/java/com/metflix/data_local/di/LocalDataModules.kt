package com.metflix.data_local.di

import com.metflix.data.source.LocalDataSource
import com.metflix.data_local.LocalDataSourceImpl
import com.metflix.data_local.database.AppDatabase
import org.koin.dsl.module

val localDataModules = module {
    factory<LocalDataSource> {
        LocalDataSourceImpl(
            AppDatabase.getDatabase(
                context = get()
            )
        )
    }


}