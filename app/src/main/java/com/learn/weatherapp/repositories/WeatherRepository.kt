package com.learn.weatherapp.repositories

import com.learn.weatherapp.model.WeatherResponse
import com.learn.weatherapp.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(): Resource<WeatherResponse>
}
