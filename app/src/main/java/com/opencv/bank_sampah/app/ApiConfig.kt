package com.opencv.bank_sampah.app

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    fun retrofitClientInstance():Retrofit{
        val gson= GsonBuilder().setLenient().create()
        return  Retrofit.Builder().baseUrl("https://haversine.mtsshifa.com/api/").addConverterFactory(
            GsonConverterFactory.create(gson)).build()
    }
}