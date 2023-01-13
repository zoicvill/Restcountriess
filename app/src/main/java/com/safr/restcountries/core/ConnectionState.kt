package com.safr.restcountries.core

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}