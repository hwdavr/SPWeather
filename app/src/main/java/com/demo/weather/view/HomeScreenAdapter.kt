package com.demo.weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.weather.R
import com.demo.weather.model.city.City

class HomeScreenAdapter(private var cities: List<City>): RecyclerView.Adapter<HomeScreenAdapter.CityViewHolder>() {

    fun updateData(data: List<City>) {
        cities = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]

        holder.cityName.text = city.name
    }

    class CityViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.findViewById(R.id.city_name)
    }

}