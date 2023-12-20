package com.example.weatherappdpk.presentation

import com.example.weatherappdpk.helper.EndPointType
import com.example.weatherappdpk.helper.MviAction

sealed interface HomeAction : MviAction {
    data object NothingAction : HomeAction
    data class GetWeatherDetailsAction(val type: EndPointType, val location: String) : HomeAction
}