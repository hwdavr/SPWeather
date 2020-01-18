package com.demo.weather.model.api

import org.junit.Test

import org.junit.Assert.*

class LocalWeatherServiceTest {
    val api = LocalWeatherService()

    @Test
    fun currentWeather() {
        val weather = api.currentWeather("London")
        assertFalse(weather.humidity.isNullOrEmpty())
        assertFalse(weather.weatherIconUrl.isNullOrEmpty())
        assertFalse(weather.weatherDesc.isNullOrEmpty())
        assertFalse(weather.temp_C.isNullOrEmpty())
        assertFalse(weather.observation_time.isNullOrEmpty())
        println(weather)
    }
}