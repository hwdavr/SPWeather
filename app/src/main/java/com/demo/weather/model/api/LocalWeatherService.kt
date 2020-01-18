package com.demo.weather.model.api

import com.demo.weather.model.apidata.entity.CurrentCondition
import com.demo.weather.model.util.WWO_LOCAL_WEATHER_URL
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import javax.inject.Inject


class LocalWeatherService  @Inject constructor() {
    private val TAG = LocalWeatherService::class.java.simpleName

    fun currentWeather(query: String): CurrentCondition {
        val weather = CurrentCondition()
        val url = URL("$WWO_LOCAL_WEATHER_URL&q=$query")
        val result = HttpConnection.getRequest(url) ?: return weather
        try {
            val mapper = ObjectMapper()
            val node = mapper.readTree(result) as? ObjectNode
            val dataNode = node?.get("data")
            val currentCondition = dataNode?.get("current_condition")?.get(0)
            weather.observation_time = currentCondition?.get("observation_time")?.textValue()
            weather.temp_C = currentCondition?.get("temp_C")?.textValue()
            weather.humidity = currentCondition?.get("humidity")?.textValue()
            weather.temp_C = currentCondition?.get("temp_C")?.textValue()
            val weatherIconJson = currentCondition?.get("weatherIconUrl")
            weather.weatherIconUrl = parseValue(weatherIconJson)
            val weatherDescJson = currentCondition?.get("weatherDesc")
            weather.weatherDesc = parseValue(weatherDescJson)
        } catch (e: JsonMappingException) {
            e.printStackTrace()
        }
        return weather
    }

    private fun parseValue(node: JsonNode?): String? {
        val resultValue = node?.get(0)
        return resultValue?.get("value")?.textValue()
    }
}