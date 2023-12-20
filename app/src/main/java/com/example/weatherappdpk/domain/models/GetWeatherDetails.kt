package com.example.weatherappdpk.domain.models


data class GetWeatherDetails(
    // location data
    var name: String,
    var country: String,
    var region: String,
    var localtime: String,

    // current data
    var observationTime: String,
    var temperature: Int,
    var weatherCode: Int,
    var weatherIcons: ArrayList<String> = arrayListOf(),
    var weatherDescriptions: ArrayList<String> = arrayListOf(),
    var windSpeed: Int,
    var windDir: String,
    var humidity: Int,
)