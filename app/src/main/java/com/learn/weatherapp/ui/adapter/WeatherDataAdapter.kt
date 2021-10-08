package com.learn.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.weatherapp.databinding.ItemWeatherDataBinding
import com.learn.weatherapp.model.Data
import com.learn.weatherapp.util.Util

class WeatherDataAdapter : RecyclerView.Adapter<WeatherDataAdapter.WeatherDataViewHolder>() {

    private val dataList = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDataViewHolder {
        return WeatherDataViewHolder(
            ItemWeatherDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherDataViewHolder, position: Int) {
        if (position != -1 && position < dataList.size) {
            holder.bindData(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateWeatherData(list: List<Data>) {
        val oldList = dataList
        dataList.clear()
        dataList.addAll(list)
        notifyItemRangeChanged(0, oldList.size)
    }

    inner class WeatherDataViewHolder(private val binding: ItemWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Data) {
            // TODO: We can parse these string after defining strings in string.xml
            binding.tvTemp.text = "${data.temp}\u2103" // \u2103 is for degreeC symbol
            binding.tvRain.text = "${data.rain}%"
            binding.tvWind.text = "${data.wind} km/h"
            binding.tvDate.text = Util.parseDate(data.time.toLong())
        }
    }

}