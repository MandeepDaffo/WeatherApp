package com.learn.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.text.SimpleDateFormat
import java.util.*


object Util {

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED

    }

    fun parseDate(date: Long, epoch: Boolean = true): String {
        val dateData = if (epoch) date * 1000 else date
        return try {
            SimpleDateFormat("MMMM dd yyyy", Locale.getDefault()).format(Date(dateData))
        } catch (e: Exception) {
            ""
        }
    }
}
