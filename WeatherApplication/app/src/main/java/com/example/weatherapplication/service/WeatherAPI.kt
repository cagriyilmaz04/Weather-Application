package com.example.weatherapplication.service

import com.example.weatherapplication.model.LastModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    // val BASE_URL="https://api.openweathermap.org/"
    @GET("data/2.5/weather?appid=af2a15bf60729e1d9748ae66012e6adc&lang=tr&units=metric")
    fun getData(@Query("q") string:String): Call<LastModel>

}