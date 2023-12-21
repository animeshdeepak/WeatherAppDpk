package com.example.weatherappdpk.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.weatherappdpk.domain.models.GetWeatherDetails
import com.example.weatherappdpk.helper.ViewState

data class HomeViewState(
    val weatherDetailsByLocation: MutableState<GetWeatherDetails?>,
    val refreshPage: MutableState<Boolean>,
) : ViewState {
    companion object {
        fun default() = HomeViewState(
            weatherDetailsByLocation = mutableStateOf(null),
            refreshPage = mutableStateOf(false),
        )
    }
}