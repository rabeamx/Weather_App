package com.rabea.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/";
    }

    var tv_location: TextView? = null
    var tv_temp: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_location = findViewById(R.id.tv_location)
        tv_temp = findViewById(R.id.tv_temp)

        fetchWeatherData()

    }

    private fun fetchWeatherData() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val response = retrofit.getWeatherData(1205733, "6f6009ae5c875c5b3956ba0bf3121e96" , "metric")

        response.enqueue(object : Callback<OpenAPI> {

            override fun onResponse(call: Call<OpenAPI>, response: Response<OpenAPI>) {

                val responseBody = response.body()!!

                tv_temp!!.text = responseBody.list[0].main.temp.toString()
                tv_location!!.text = responseBody.list[0].main.temp.toString()

            }

            override fun onFailure(call: Call<OpenAPI>, failed: Throwable) {

                Log.d("DATA", failed.toString())

            }
        })



    }

}