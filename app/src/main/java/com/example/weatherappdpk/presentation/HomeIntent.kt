package com.example.weatherappdpk.presentation

import com.example.weatherappdpk.helper.EndPointType
import com.example.weatherappdpk.helper.MviIntent

sealed interface HomeIntent : MviIntent {
    data class GetWeatherDetailsIntent(val type: EndPointType, val location: String): HomeIntent
}