package com.example.weatherapplication.service

import com.example.weatherapplication.model.LastModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL :String by lazy {
    "https://api.openweathermap.org/"
}
class APIService {
    private val Api= Retrofit.Builder().baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getData(str:String): Call<LastModel> {
        return Api.getData(str)
    }

}