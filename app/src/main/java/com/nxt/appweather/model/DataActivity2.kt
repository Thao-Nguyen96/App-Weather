package com.nxt.appweather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataActivity2(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: ArrayList<DataActivity2Info>
)

data class DataActivity2Info(
    val dt: Int,
    val main: MainInfo,
    val weather: ArrayList<WeatherDay2Info>
)

data class MainInfo(
    @SerializedName("temp_min")
    @Expose
    val tempMin: Double,
    @SerializedName("temp_max")
    @Expose
    val tempMax: Double
)

data class WeatherDay2Info(val description: String, val icon: String)
