package com.safr.restcountries.data.datasourse

import com.safr.restcountries.data.model.CountryItem

interface ApiDataSource {
    suspend fun getLinstLand(): List<CountryItem>?
}