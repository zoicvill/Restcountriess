package com.safr.restcountries.data.datasourse


import com.safr.restcountries.data.ApiLand
import com.safr.restcountries.data.model.CountryItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataSourceImpl @Inject constructor(private val api: ApiLand): ApiDataSource {
    override suspend fun getLinstLand(): List<CountryItem>? {
        return api.getLand()
    }

}