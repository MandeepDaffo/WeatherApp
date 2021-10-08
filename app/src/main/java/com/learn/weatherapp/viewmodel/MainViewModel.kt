package com.learn.weatherapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.weatherapp.model.WeatherResponse
import com.learn.weatherapp.repositories.WeatherRepository
import com.learn.weatherapp.util.Resource
import com.learn.weatherapp.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _weatherResponse: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData(Resource.None)
    val weatherResponse get() = _weatherResponse

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MainViewModel", "Exception is $throwable")
        _weatherResponse.postValue(Resource.Error(throwable.message ?: "Unknown Error"))
    }

    init {
        getWeatherData()
    }

    private fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _weatherResponse.postValue(Resource.Loading)
            if (Util.isInternetAvailable(context)) {
                _weatherResponse.postValue(weatherRepository.getWeatherData())
            } else {
                _weatherResponse.postValue(Resource.Error("No Internet Available"))
            }
        }
    }
}