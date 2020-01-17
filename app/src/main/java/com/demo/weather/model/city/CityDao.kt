package com.demo.weather.model.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @get:Query("SELECT * FROM city ORDER BY updated_at DESC LIMIT 10")
    val latest: List<City>

    @Insert
    fun insertAll(vararg cities: City)

    @Insert
    fun insert(city: City)
}