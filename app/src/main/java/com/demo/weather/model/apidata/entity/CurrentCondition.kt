package com.demo.weather.model.apidata.entity

import kotlinx.serialization.Serializable

@Serializable
data class CurrentCondition(
    var observation_time: String? = null,
    var temp_C: String? = null,
    var weatherIconUrl: String? = null,
    var weatherDesc: String? = null,
    var humidity: String? = null
)