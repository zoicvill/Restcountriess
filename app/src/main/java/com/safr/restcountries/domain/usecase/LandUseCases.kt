package com.safr.restcountries.domain.usecase


import com.safr.restcountries.core.DataState
import com.safr.restcountries.data.model.CountryItem
import com.safr.restcountries.domain.repo.LandRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class LandUseCases @Inject constructor(private val rep: LandRepository) {
   fun lander(): Flow<DataState<List<CountryItem>?>>{
       return rep.land()
    }
}