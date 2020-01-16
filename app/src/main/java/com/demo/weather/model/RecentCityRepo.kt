package com.demo.weather.model

import com.demo.weather.model.util.OpenrationListener

class RecentCityRepo : CityDataSource {
    override fun getCities(listner: OpenrationListener) {
        val dummyList = listOf<City>(
            City(0, "Singapore"),
            City(1, "Beijing"),
            City(2, "London")
        )
        listner.onSuccess(dummyList)
    }


}