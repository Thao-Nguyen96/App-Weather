package com.nxt.appweather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nxt.appweather.R
import com.nxt.appweather.adapter.AdapterWeather
import com.nxt.appweather.api.RetroInstance
import com.nxt.appweather.model.DataActivity2
import com.nxt.appweather.model.DataActivity2Info
import com.nxt.appweather.viewmodel.ViewModelMainDay
import kotlinx.android.synthetic.main.activity_day.*

//https://api.openweathermap.org/data/2.5/forecast?q=saigon&units=metric&cnt=7&appid=8aa948f39a95d3ad675cde059b15d016

class DayActivity : AppCompatActivity() {

    private var adapter: AdapterWeather? = null
    private var list: ArrayList<DataActivity2Info>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day)

        textView.text = intent.getStringExtra("name")
        list = ArrayList()
        adapter = AdapterWeather(list!!)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        val viewModel = ViewModelProviders.of(this).get(ViewModelMainDay::class.java)
        viewModel.getListDataDay2ObServer().observe(this, Observer<DataActivity2> {
            if (it != null) {
                println(it)
                adapter?.list = it.list
                adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCallDay2(
            intent.getStringExtra("name").toString(),
            "metric",
            7,
            RetroInstance.API_KEY
        )
    }
}