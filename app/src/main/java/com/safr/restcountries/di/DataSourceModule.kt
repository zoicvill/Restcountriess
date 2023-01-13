package com.safr.restcountries.di

import com.safr.restcountries.data.ApiLand
import com.safr.restcountries.data.datasourse.ApiDataSource
import com.safr.restcountries.data.datasourse.ApiDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideApiDataSource(api: ApiLand): ApiDataSource {
        return ApiDataSourceImpl(api)
    }
}