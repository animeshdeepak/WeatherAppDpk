package com.example.weatherappdpk.helper

sealed class ApiResult<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : ApiResult<T>(data)
    class Error<T>(message: String) : ApiResult<T>(message = message)
}

inline infix fun <U, V> ApiResult<U>.map(transform: (U) -> V): ApiResult<V> {
    return when (this) {
        is ApiResult.Success -> ApiResult.Success(transform(data!!))
        is ApiResult.Error -> ApiResult.Error(message ?: "Something went wrong!")
    }
}

inline infix fun <U> ApiResult<U>.onSuccess(action: (U) -> Unit): ApiResult<U> {
    if (this is ApiResult.Success)
        action(data!!)
    return this
}

inline infix fun <U> ApiResult<U>.onFailure(action: (String) -> Unit): ApiResult<U> {
    if (this is ApiResult.Error)
        action(message ?: "Something went wrong!")
    return this
}
