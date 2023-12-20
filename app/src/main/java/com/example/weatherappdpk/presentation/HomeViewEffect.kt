package com.example.weatherappdpk.presentation

import com.example.weatherappdpk.helper.ViewEffect

sealed interface HomeViewEffect : ViewEffect {
    data object Nothing : HomeViewEffect
    data class Error(val message: String, val code: Int) : HomeViewEffect
    data class ShowToast(val message: String?) : HomeViewEffect
}