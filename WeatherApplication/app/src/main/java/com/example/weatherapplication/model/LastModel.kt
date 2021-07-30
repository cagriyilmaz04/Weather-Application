package com.example.weatherapplication.model

import com.google.gson.annotations.SerializedName


data class LastModel(
    @SerializedName("coord")
    val coord:Coord,
    @SerializedName("weather")
    val weather:List<Weather>,
    @SerializedName("main")
    val main:Main,
    @SerializedName("name")
    val cityName:String)