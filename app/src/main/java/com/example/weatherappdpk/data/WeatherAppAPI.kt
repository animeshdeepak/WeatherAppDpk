package com.example.weatherappdpk.data

import com.example.weatherappdpk.data.models.response.GetWeatherDetailsDTO
import com.example.weatherappdpk.helper.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAppAPI {
    @GET("/{api_type}")
    suspend fun getWeatherDetailsByLocation(
        @Path("api_type") apiType: String,
        @Query("access_key") accessKey: String = Constants.API_KEY,
        @Query("query") location: String,
    ): Response<GetWeatherDetailsDTO>
}