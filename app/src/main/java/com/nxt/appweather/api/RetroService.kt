package com.nxt.appweather.api

import com.nxt.appweather.model.Data
import com.nxt.appweather.model.DataActivity2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

        //https://api.openweathermap.org/data/2.5/weather?q=saigon&units=metric&appid=8aa948f39a95d3ad675cde059b15d016
    // https://api.openweathermap.org/data/2.5/forecast?q=saigon&units=metric&cnt=7&appid=8aa948f39a95d3ad675cde059b15d016
//data la q, units
    @GET("weather")
    fun getDataFomApi(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<Data>


    @GET("forecast")
    fun getDataActivity2FomApi(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("cnt") day: Int,
        @Query("appid") appid: String
    ): Call<DataActivity2>


}