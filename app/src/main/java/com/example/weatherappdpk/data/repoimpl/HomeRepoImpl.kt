package com.example.weatherappdpk.data.repoimpl

import com.example.weatherappdpk.data.WeatherAppAPI
import com.example.weatherappdpk.data.models.response.GetWeatherDetailsDTO
import com.example.weatherappdpk.domain.repo.HomeRepo
import com.example.weatherappdpk.helper.ApiResult
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val service: WeatherAppAPI
) : HomeRepo {
    override suspend fun getWeatherDetailsByLocation(
        apiType: String,
        location: String,
    ): ApiResult<GetWeatherDetailsDTO> {
        val response = service.getWeatherDetailsByLocation(apiType = apiType, location = location)

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiResult.Success(resultResponse)
            }
        }
        return ApiResult.Error(response.message())
    }
}