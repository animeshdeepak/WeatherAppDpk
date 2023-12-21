package com.example.weatherappdpk.helper

import com.example.weatherappdpk.R

fun getRequiredDrawable(weatherCode: Int): Int {
    return when (weatherCode) {
        113 -> R.drawable.ic_sun // Sunny, Clear
        116, 119, 122 -> R.drawable.ic_cloudy // Partly cloudy, Cloudy, Overcast
        143 -> R.drawable.ic_mist // Mist
        176, 263, 266, 281, 284, 293, 296, 299 -> R.drawable.ic_rainy // Patchy rain possible, Freezing drizzle, Heavy freezing drizzle, Patchy light rain, Light rain, Moderate rain at times
        179, 182, 185, 227, 230 -> R.drawable.ic_snow // Patchy snow possible, Patchy sleet possible, Patchy freezing drizzle possible, Blowing snow, Blizzard,Blizzard
        200 -> R.drawable.ic_storm // Thundery outbreaks possible
        248, 260 -> R.drawable.ic_fog // Fog
        302, 305, 308, 311 -> R.drawable.ic_heavy_rain // Moderate rain, Heavy rain at times, Heavy rain, Light freezing rain
        else -> R.drawable.ic_no_photo
    }
}