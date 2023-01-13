package com.safr.restcountries.domain.repo

import com.safr.restcountries.core.DataState
import com.safr.restcountries.data.model.CountryItem
import kotlinx.coroutines.flow.Flow

interface LandRepository {
    fun land(): Flow<DataState<List<CountryItem>?>>
}