package com.rabea.myweatherapp

data class OpenAPI(
    val city: City,
    val id: Int,
    val cnt: Int,
    val cod: String,
    val list: List<API>,
    val message: Int
)