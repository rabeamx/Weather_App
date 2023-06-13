package com.rabea.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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
    var tv_humidity: TextView? = null
    var tv_air_flow: TextView? = null
    var tv_weather_type: TextView? = null
    var tv_time: TextView? = null

    var dayOne: TextView? = null
    var tempOne: TextView? = null

    var dayTwo: TextView? = null
    var tempTwo: TextView? = null

    var dayThree: TextView? = null
    var tempThree: TextView? = null

    var dayFour: TextView? = null
    var tempFour: TextView? = null

    var dayFive: TextView? = null
    var tempFive: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_location = findViewById(R.id.tv_location)
        tv_temp = findViewById(R.id.tv_temp)
        tv_humidity = findViewById(R.id.tv_humidity)
        tv_air_flow = findViewById(R.id.tv_air_flow)
        tv_weather_type = findViewById(R.id.tv_weather_type)
        tv_time = findViewById(R.id.tv_time)

        dayOne = findViewById(R.id.dayOne)
        tempOne = findViewById(R.id.tempOne)

        dayTwo = findViewById(R.id.dayTwo)
        tempTwo = findViewById(R.id.tempTwo)

        dayThree = findViewById(R.id.dayThree)
        tempThree = findViewById(R.id.tempThree)

        dayFour = findViewById(R.id.dayFour)
        tempFour = findViewById(R.id.tempFour)

        dayFive = findViewById(R.id.dayFive)
        tempFive = findViewById(R.id.tempFive)

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
                tv_location!!.text = responseBody.city.name.toString()
                tv_humidity!!.text = responseBody.list[0].main.humidity.toString()
                tv_air_flow!!.text = responseBody.list[0].wind.speed.toString()
                tv_weather_type!!.text = responseBody.list[0].weather[0].main.toString()
                tv_time!!.text = responseBody.list[0].dt_txt.toString()

                dayOne!!.text = responseBody.list[7].dt_txt.toString()
                tempOne!!.text = responseBody.list[7].main.temp.toString()

                dayTwo!!.text = responseBody.list[15].dt_txt.toString()
                tempTwo!!.text = responseBody.list[15].main.temp.toString()

                dayThree!!.text = responseBody.list[23].dt_txt.toString()
                tempThree!!.text = responseBody.list[23].main.temp.toString()

                dayFour!!.text = responseBody.list[31].dt_txt.toString()
                tempFour!!.text = responseBody.list[31].main.temp.toString()

                dayFive!!.text = responseBody.list[39].dt_txt.toString()
                tempFive!!.text = responseBody.list[39].main.temp.toString()

            }

            override fun onFailure(call: Call<OpenAPI>, failed: Throwable) {

                Log.d("DATA", failed.toString())

            }
        })



    }

}