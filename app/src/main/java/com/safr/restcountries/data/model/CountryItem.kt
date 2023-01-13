package com.safr.restcountries.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryItem(val name: String?,
                       val flags: Flags?,
                       val timezones:  ArrayList<String>        = arrayListOf(),
                       val currencies: ArrayList<Currencies>    = arrayListOf(),
                       val capital: String?,
                       val region: String?): Parcelable
@Parcelize
data class Flags (

    @SerializedName("svg" ) var svg : String? = null,
    @SerializedName("png" ) var png : String? = null

): Parcelable

@Parcelize
data class Currencies (

    @SerializedName("code"   ) var code   : String? = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("symbol" ) var symbol : String? = null

): Parcelable