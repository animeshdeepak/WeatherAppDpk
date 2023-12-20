package com.example.weatherappdpk.helper

enum class EndPointType(val type: String) {
    CURRENT("current"),
    HISTORICAL("historical"),
    FORECAST("forecast"),
    AUTOCOMPLETE("autocomplete"),
}