package com.safr.restcountries.data.repository

import android.util.Log
import com.safr.restcountries.core.DataState
import com.safr.restcountries.core.ErrorState
import com.safr.restcountries.data.datasourse.ApiDataSource
import com.safr.restcountries.data.model.CountryItem
import com.safr.restcountries.domain.repo.LandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LandRepositoryImpl @Inject constructor(val api: ApiDataSource): LandRepository{

    override fun land(): Flow<DataState<List<CountryItem>?>> = flow {
        emit(DataState.Loading())
        emit(DataState.Success(api.getLinstLand()))
    }.catch {
        Log.e("lol" , "catch ${it.message}")
        emit(DataState.Error(error = ErrorState.NO_INTERNET_CONNECTION)) }
        .flowOn(Dispatchers.IO)
}