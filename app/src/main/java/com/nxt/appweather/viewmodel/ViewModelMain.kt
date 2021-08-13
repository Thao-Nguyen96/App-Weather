package com.nxt.appweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nxt.appweather.api.RetroInstance
import com.nxt.appweather.api.RetroService
import com.nxt.appweather.model.Data
import retrofit2.Call
import retrofit2.Response

class ViewModelMain : ViewModel() {

    var listData: MutableLiveData<Data> = MutableLiveData()

    fun getListDataObServer(): MutableLiveData<Data> {
        return listData
    }

    fun makeApiCall(country: String, units: String, keyId: String) {
        RetroInstance.getInstance().create(RetroService::class.java)
            .getDataFomApi(country, units, keyId)
            .enqueue(object : retrofit2.Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful) {
                        println(response)
                        listData.postValue(response.body())
                    } else {
                        listData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    listData.postValue(null)
                }

            })
    }
}