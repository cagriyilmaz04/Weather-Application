package com.example.weatherapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.model.LastModel
import com.example.weatherapplication.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainviewmodel(): ViewModel() {
    val weather_data = MutableLiveData<LastModel>()
    val is_it_loaded= MutableLiveData<Boolean>()
    fun getData(cityname:String){
        val func= APIService()
        func.getData(cityname).enqueue(object : Callback<LastModel> {
            override fun onResponse(call: Call<LastModel>, response: Response<LastModel>) {
                is_it_loaded.value=true
                if(response.isSuccessful){
                    weather_data.value=response.body()
                    is_it_loaded.value=true

                }
            }
            override fun onFailure(call: Call<LastModel>, t: Throwable) {
                is_it_loaded.value=false
            }

        })
    }


}