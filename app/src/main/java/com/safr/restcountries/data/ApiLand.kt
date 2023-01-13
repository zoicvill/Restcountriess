package com.safr.restcountries.data

import com.safr.restcountries.data.model.CountryItem
import retrofit2.http.GET

interface ApiLand {
    @GET("all")
    suspend fun getLand():List<CountryItem>

}
