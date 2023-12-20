package com.example.weatherappdpk.presentation

import com.example.weatherappdpk.domain.models.GetWeatherDetails
import com.example.weatherappdpk.helper.MviResult

sealed interface HomeResult : MviResult {
    data object Nothing : HomeResult
    data class GetWeatherDetailsResult(val result: GetWeatherDetails) : HomeResult
}