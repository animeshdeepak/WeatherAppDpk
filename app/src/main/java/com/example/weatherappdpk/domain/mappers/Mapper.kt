package com.example.weatherappdpk.domain.mappers

abstract class Mapper<DOMAIN, DTO> {
    abstract fun dtoToDomain(dto: DTO): DOMAIN
    abstract fun domainToDto(domain: DOMAIN): DTO
}