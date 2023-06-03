package com.opencv.bank_sampah.app

import com.opencv.bank_sampah.model.data.Koin
import com.opencv.bank_sampah.model.request.inputOutliteRequest
import com.opencv.bank_sampah.model.request.registerRequest
import com.opencv.bank_sampah.model.request.userRequest
import com.opencv.bank_sampah.model.response.inputOutliteResponse
import com.opencv.bank_sampah.model.response.koinResponse
import com.opencv.bank_sampah.model.response.userResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(
        @Body request: userRequest
    ): Call<userResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(
        @Body request: registerRequest
    ): Call<userResponse>

    @Headers("Content-Type: application/json")
    @POST("outlets")
    fun outlets(
        @Body request: inputOutliteRequest
    ): Call<inputOutliteResponse>

    @Headers("Content-Type: application/json")
    @POST("logout")
    fun logout(
        @Body request: inputOutliteRequest
    ): Call<userResponse>

    @Headers("Content-Type: application/json")
    @GET("koins")
    fun koin():Call<koinResponse>

    @Headers("Content-Type: application/json")
    @GET("penjemputans")
    fun penjemputan():Call<koinResponse>




}