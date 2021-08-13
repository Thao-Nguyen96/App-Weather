package com.nxt.appweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nxt.appweather.api.RetroInstance
import com.nxt.appweather.api.RetroService
import com.nxt.appweather.model.DataActivity2
import retrofit2.Call
import retrofit2.Response

class ViewModelMainDay : ViewModel() {
    var listDataDay2: MutableLiveData<DataActivity2> = MutableLiveData()

    fun getListDataDay2ObServer(): MutableLiveData<DataActivity2> {
        return listDataDay2
    }

    fun makeApiCallDay2(country: String, units: String, day: Int, apiKey: String) {
        RetroInstance.getInstance().create(RetroService::class.java)
            .getDataActivity2FomApi(country, units, day, apiKey)
            .enqueue(object : retrofit2.Callback<DataActivity2> {
                override fun onResponse(
                    call: Call<DataActivity2>,
                    response: Response<DataActivity2>
                ) {
                    if (response.isSuccessful) {
                        listDataDay2.postValue(response.body())
                    } else {
                        listDataDay2.postValue(null)
                    }
                }
                override fun onFailure(call: Call<DataActivity2>, t: Throwable) {
                    listDataDay2.postValue(null)
                }
            })
    }
}