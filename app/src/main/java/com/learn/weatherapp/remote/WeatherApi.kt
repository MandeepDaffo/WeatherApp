package com.learn.weatherapp.remote

import com.learn.weatherapp.model.WeatherResponse
import retrofit2.http.GET

interface WeatherApi {

    @GET("v2/5d3a99ed2f0000bac16ec13a")
    suspend fun getWeatherData(): WeatherResponse

}
