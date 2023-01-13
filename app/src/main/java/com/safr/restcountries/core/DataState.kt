package com.safr.restcountries.core

sealed class DataState<out T>(val data: T? = null, val message: ErrorState? = null) {
        class Success<T>(data: T) : DataState<T>(data)
        class Error<T>(error: ErrorState, data: T? = null) : DataState<T>(data, error)
        class Loading<T>(data: T? = null) : DataState<T>(data)
    }
enum class ErrorState {
    NO_INTERNET_CONNECTION,
}