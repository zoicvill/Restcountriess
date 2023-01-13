package com.safr.restcountries.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safr.restcountries.core.ConnectionState.Available
import com.safr.restcountries.core.ConnectionState.Unavailable
import com.safr.restcountries.core.DataState
import com.safr.restcountries.core.ErrorState
import com.safr.restcountries.core.NetworkStatusListener
import com.safr.restcountries.data.model.CountryItem

import com.safr.restcountries.domain.usecase.LandUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class ViewModelLand
@Inject constructor(
     val land: LandUseCases,
     val networkStatusListener: NetworkStatusListener,
) : ViewModel() {

    private val _landContent = MutableStateFlow<
            DataState<List<CountryItem>?>>(DataState.Loading())
    var landContent = _landContent.asStateFlow()


    private val _mainContentLoading = MutableStateFlow(false)
    var mainContentLoading = _mainContentLoading.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.M)
    fun getlandContent(){
        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
              Available -> {
                  getLandInfo()
                  Log.e("lol" , "Available")
                }
                Unavailable -> {
                    Log.e("lol" , "Unavailable")
                    _mainContentLoading.value = true
                    if (_landContent.value.data != null) _landContent.value =
                        DataState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getLandInfo() {
        land.lander().onEach { result ->
            when (result) {
                is DataState.Success -> {
                    _landContent.value = result
                    _mainContentLoading.value = false
                    Log.e("lol" , "DataState.Success  ${result.data?.size}")
                }
                is DataState.Loading -> {
                    _mainContentLoading.value = false
                    Log.e("lol" , "Loading")
                }
                is DataState.Error -> {
                    _mainContentLoading.value = true
                    _landContent.value = result
                    Log.e("lol" , "DataState.Error  ${result.message?.name}")
                }
            }

        }.launchIn(viewModelScope)

    }


}