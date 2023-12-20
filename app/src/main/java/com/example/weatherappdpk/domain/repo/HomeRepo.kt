package com.example.weatherappdpk.domain.repo

import com.example.weatherappdpk.data.models.response.GetWeatherDetailsDTO
import com.example.weatherappdpk.helper.ApiResult

interface HomeRepo {
    suspend fun getWeatherDetailsByLocation(
        apiType: String,
        location: String,
    ): ApiResult<GetWeatherDetailsDTO>
}