package com.nxt.appweather.model

data class Data(
    val name: String,
    val weather: ArrayList<WeatherInfo>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val sys : SysInfo
)

data class WeatherInfo(val icon: String)
data class Main(val temp: Double, val humidity: Int)
data class Wind(val speed: Double)
data class Clouds(val all: Int)
data class SysInfo(val country: String)

