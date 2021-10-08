package com.learn.weatherapp.repositories

import com.learn.weatherapp.model.WeatherResponse
import com.learn.weatherapp.remote.WeatherApi
import com.learn.weatherapp.util.Resource

class WeatherRepositoryImpl(
    val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(): Resource<WeatherResponse> {
        return try {
            Resource.Success(dataValue = api.getWeatherData())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }
}
