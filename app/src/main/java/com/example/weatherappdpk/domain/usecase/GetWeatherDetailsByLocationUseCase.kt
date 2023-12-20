package com.example.weatherappdpk.domain.usecase

import com.example.weatherappdpk.domain.mappers.GetWeatherDetailsMapper
import com.example.weatherappdpk.domain.models.GetWeatherDetails
import com.example.weatherappdpk.domain.repo.HomeRepo
import com.example.weatherappdpk.helper.ApiResult
import com.example.weatherappdpk.helper.EndPointType
import com.example.weatherappdpk.helper.map
import javax.inject.Inject

class GetWeatherDetailsByLocationUseCase @Inject constructor(
    private val homeRepo: HomeRepo,
    private val mapper: GetWeatherDetailsMapper,
) : UseCaseSuspend<Params, ApiResult<GetWeatherDetails>> {
    override suspend fun invoke(params: Params): ApiResult<GetWeatherDetails> {
        return homeRepo.getWeatherDetailsByLocation(
            params.endPointType.type,
            location = params.location,
        ).map(mapper::dtoToDomain)
    }
}

data class Params(
    val endPointType: EndPointType,
    val location: String,
)