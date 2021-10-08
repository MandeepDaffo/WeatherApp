package com.learn.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.weatherapp.ui.adapter.WeatherDataAdapter
import com.learn.weatherapp.databinding.ActivityMainBinding
import com.learn.weatherapp.model.WeatherResponse
import com.learn.weatherapp.util.Resource
import com.learn.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val weatherAdapter: WeatherDataAdapter by lazy { WeatherDataAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setObservers()
    }

    private fun initView() {
        binding.rvWeatherData.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setObservers() {
        mainViewModel.weatherResponse.observe(this, { weatherResponse ->
            onWeatherApiResponse(weatherResponse)
        })
    }

    private fun onWeatherApiResponse(weatherResponse: Resource<WeatherResponse>) {
        hideProgress()
        when (weatherResponse) {
            is Resource.Error -> {
                Toast.makeText(this, weatherResponse.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
            }
            Resource.Loading -> {
                showProgress()
            }
            Resource.None -> {

            }
            is Resource.Success -> {
                weatherAdapter.updateWeatherData(weatherResponse.data?.data ?: listOf())
            }
        }
    }

    private fun showProgress() {
        if (!isFinishing and !binding.progressBar.isVisible) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideProgress() {
        if (!isFinishing and binding.progressBar.isVisible) {
            binding.progressBar.visibility = View.GONE
        }
    }

}