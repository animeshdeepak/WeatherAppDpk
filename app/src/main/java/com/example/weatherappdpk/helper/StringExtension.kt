package com.example.weatherappdpk.helper

val String.Companion.EMPTY: String by lazy { "" }
val Int.Companion.EMPTY: Int by lazy { Int.MIN_VALUE }

/** This Extension field of type string returns the value of string or empty */
val String?.value: String
    get() = this ?: String.EMPTY