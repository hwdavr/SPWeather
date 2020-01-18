package com.demo.weather.model.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class City(
    @ColumnInfo(name = "city_name") val name: String,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}