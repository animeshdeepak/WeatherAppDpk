package com.example.weatherappdpk.domain.mappers

import com.example.weatherappdpk.data.models.response.GetWeatherDetailsDTO
import com.example.weatherappdpk.domain.models.GetWeatherDetails
import com.example.weatherappdpk.helper.EMPTY
import javax.inject.Inject

class GetWeatherDetailsMapper @Inject constructor() :
    Mapper<GetWeatherDetails, GetWeatherDetailsDTO>() {
    override fun dtoToDomain(dto: GetWeatherDetailsDTO): GetWeatherDetails {
        return GetWeatherDetails(
            name = dto.location?.name ?: String.EMPTY,
            country = dto.location?.country ?: String.EMPTY,
            region = dto.location?.region ?: String.EMPTY,
            localtime = dto.location?.localtime ?: String.EMPTY,

            observationTime = dto.current?.observationTime ?: String.EMPTY,
            temperature = dto.current?.temperature ?: Int.EMPTY,
            weatherCode = dto.current?.weatherCode ?: Int.EMPTY,
            weatherIcons = dto.current?.weatherIcons ?: arrayListOf(),
            weatherDescriptions = dto.current?.weatherDescriptions ?: arrayListOf(),
            windSpeed = dto.current?.windSpeed ?: Int.EMPTY,
            windDir = dto.current?.windDir ?: String.EMPTY,
            humidity = dto.current?.humidity ?: Int.EMPTY,
        )
    }

    override fun domainToDto(domain: GetWeatherDetails): GetWeatherDetailsDTO {
        throw NotImplementedError("override and implement this method")
    }
}