package com.example.weatherapplication.model

import com.google.gson.annotations.SerializedName


data class Main (
    @SerializedName("temp")
    val temp:Double,
    @SerializedName("humidity")
    val humudity:Double)