package com.demo.weather.model.apidata

import com.demo.weather.model.apidata.entity.SearchApiResult
import kotlinx.serialization.*

@Serializable
data class SearchApiResponse(
    val search_api: SearchApiResult
)
