package com.demo.weather.model.api

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class SearchCityServiceTest {
    val api = SearchCityService()

    @Before
    fun setUp() {
    }

    @Test
    fun queryCities() {
        val cities = api.queryCities("London")
        assertTrue(cities.size > 0)
        println(cities)
    }
}