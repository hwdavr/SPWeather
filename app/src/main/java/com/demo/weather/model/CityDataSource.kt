package com.demo.weather.model

import com.demo.weather.model.util.OpenrationListener

interface CityDataSource {

    fun getCities(listner: OpenrationListener)
}