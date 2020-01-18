package com.demo.weather.model.apidata.entity

import kotlinx.serialization.Serializable

@Serializable
data class SearchApiResult(
    val result: List<CityResult>
)