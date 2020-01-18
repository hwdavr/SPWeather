package com.demo.weather.model.api

import android.util.Log
import com.demo.weather.model.apidata.SearchApiResponse
import com.demo.weather.model.city.City
import com.demo.weather.model.util.WWO_SEARCH_URL
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonDecodingException
import java.io.IOException
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

class SearchCityService @Inject constructor() {
    private val TAG = SearchCityService::class.java.simpleName

    fun queryCities(query: String): List<City> {
        val cities = mutableListOf<City>()
        val url = URL("$WWO_SEARCH_URL&q=$query")
        val result = HttpConnection.getRequest(url) ?: return cities
        try {
            val json = Json(JsonConfiguration.Stable)
            val searchResults = json.parse(SearchApiResponse.serializer(), result)
            for (entry in searchResults.search_api.result) {
                cities.add(City(entry.areaName[0].value, 0))
            }
        } catch (e: JsonDecodingException) {
            e.printStackTrace()
        }
        return cities
    }
}