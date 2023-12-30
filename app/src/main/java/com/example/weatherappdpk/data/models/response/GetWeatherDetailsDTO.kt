package com.example.weatherappdpk.data.models.response

import com.google.gson.annotations.SerializedName


data class GetWeatherDetailsDTO(
    @SerializedName("request") var request: Request? = Request(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("current") var current: Current? = Current()
)

data class Request(
    @SerializedName("type") var type: String? = null,
    @SerializedName("query") var query: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("unit") var unit: String? = null
)


data class Location(
    @SerializedName("name") var name: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("lat") var lat: String? = null,
    @SerializedName("lon") var lon: String? = null,
    @SerializedName("timezone_id") var timezoneId: String? = null,
    @SerializedName("localtime") var localtime: String? = null,
    @SerializedName("localtime_epoch") var localtimeEpoch: Int? = null,
    @SerializedName("utc_offset") var utcOffset: String? = null
)

data class Current(
    @SerializedName("observation_time") var observationTime: String? = null,
    @SerializedName("temperature") var temperature: Int? = null,
    @SerializedName("weather_code") var weatherCode: Int? = null,
    @SerializedName("weather_icons") var weatherIcons: ArrayList<String> = arrayListOf(),
    @SerializedName("weather_descriptions") var weatherDescriptions: ArrayList<String> = arrayListOf(),
    @SerializedName("wind_speed") var windSpeed: Int? = null,
    @SerializedName("wind_degree") var windDegree: Int? = null,
    @SerializedName("wind_dir") var windDir: String? = null,
    @SerializedName("pressure") var pressure: Int? = null,
    @SerializedName("precip") var precip: Float? = null,
    @SerializedName("humidity") var humidity: Int? = null,
    @SerializedName("cloudcover") var cloudcover: Int? = null,
    @SerializedName("feelslike") var feelslike: Int? = null,
    @SerializedName("uv_index") var uvIndex: Int? = null,
    @SerializedName("visibility") var visibility: Int? = null
)