package com.rabea.myweatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("forecast")
    fun getWeatherData(@Query("id") id: Int,
                       @Query("appid") appid: String,
                       @Query("units") units: String): Call<OpenAPI>
}