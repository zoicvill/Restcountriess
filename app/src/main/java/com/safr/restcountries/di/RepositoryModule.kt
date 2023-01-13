package com.safr.restcountries.di

import com.safr.restcountries.data.datasourse.ApiDataSource
import com.safr.restcountries.data.repository.LandRepositoryImpl
import com.safr.restcountries.domain.repo.LandRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCocktailRepository(
        lanApi: ApiDataSource,
    ): LandRepository {
        return LandRepositoryImpl(api = lanApi)
    }
}