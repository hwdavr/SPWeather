package com.demo.weather.model.city

import com.demo.weather.model.util.OpenrationListener

interface CityDataSource {

    fun insertCity(city: City)
    fun getCities(listner: OpenrationListener)
}