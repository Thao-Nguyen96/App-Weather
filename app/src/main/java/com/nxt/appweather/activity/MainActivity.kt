package com.nxt.appweather.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nxt.appweather.R
import com.nxt.appweather.viewmodel.ViewModelMain
import com.nxt.appweather.api.RetroInstance
import com.nxt.appweather.model.Data
import kotlinx.android.synthetic.main.activity_main.*

//img : https://openweathermap.org/img/w/+"icon"+.png

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel = ViewModelProviders.of(this).get(ViewModelMain::class.java)

        viewModel.makeApiCall("hanoi", "metric", RetroInstance.API_KEY)

        viewModel.getListDataObServer().observe(this, Observer<Data> {
            if (it != null) {
                println(it)
                tv_city.text = it.name
                tv_country.text = it.sys.country
                tv_temp.text = it.main.temp.toInt().toString() + "°C"
                tv_humidity.text = it.main.humidity.toString() + "%"
                tv_cloud.text = it.clouds.all.toString() + "%"
                tv_wind.text = it.wind.speed.toString() + "m/s"

                val icon = it.weather[0].icon

                Glide.with(this)
                    .load("https://openweathermap.org/img/w/$icon.png")
                    .into(img_icon_cloud)

            }
        })
        btn_ok.setOnClickListener {
            viewModel.makeApiCall(edt_nhap.text.toString(), "metric", RetroInstance.API_KEY)
        }
        tv_day.setOnClickListener {
            val intent = Intent(this, DayActivity::class.java)
            intent.putExtra("name", edt_nhap.text.toString())
            startActivity(intent)
        }
    }
}