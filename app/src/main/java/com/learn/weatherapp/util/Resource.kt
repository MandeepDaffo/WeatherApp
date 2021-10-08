package com.learn.weatherapp.util

sealed class Resource<out T>(val data: T? = null, val message: String? = null, val status: Status) {
    data class Success<T>(val dataValue: T) : Resource<T>(data = dataValue, status = Status.Success)
    object None : Resource<Nothing>(status = Status.None)
    object Loading : Resource<Nothing>(status = Status.Loading)
    data class Error(val messageValue: String?) : Resource<Nothing>(message = messageValue, status = Status.Error)
}


enum class Status {
    None,
    Loading,
    Success,
    Error
}
