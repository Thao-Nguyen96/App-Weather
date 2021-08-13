package com.nxt.appweather.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nxt.appweather.R
import com.nxt.appweather.model.DataActivity2Info
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class AdapterWeather(var list: ArrayList<DataActivity2Info>) :
    RecyclerView.Adapter<AdapterWeather.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWeather.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AdapterWeather.MyViewHolder, position: Int) {
        holder.tempMin.tv_temp_min.text = list[position].main.tempMin.toInt().toString() + "°C"
        holder.tempMax.text = list[position].main.tempMax.toInt().toString() + "°C"
        holder.description.text = list[position].weather[0].description
        val icon = list[position].weather[0].icon
        Glide.with(holder.itemView.context)
            .load("https://openweathermap.org/img/w/$icon.png")
            .into(holder.icon)
        val day = list[position].dt
        Log.d("abcd", day.toString())

        val simpleDateFormat = SimpleDateFormat("EEEE \n dd-MMM-yyyy")
        val dateTime = simpleDateFormat.format(day)
       holder.day.text = dateTime

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tempMin = view.tv_temp_min
        var tempMax = view.tv_max
        var description = view.tv_des
        var icon = view.img_icon2
        var day = view.tv_day2
    }
}