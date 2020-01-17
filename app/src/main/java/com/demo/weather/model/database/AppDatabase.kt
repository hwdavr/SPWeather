package com.demo.weather.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.weather.model.city.City
import com.demo.weather.model.city.CityDao

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}