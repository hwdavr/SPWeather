package com.demo.weather.model.city

import android.content.Context
import android.util.Log
import com.demo.weather.model.util.OpenrationListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecentCityRepoTest {
    // Init with Main Coroutine Scope
    var dao: CityDao = mock(CityDao::class.java)
    var repo = RecentCityRepo(MainScope(), dao)

    @Before
    fun setUp() {

    }

    @Test
    fun insert() {
        // insert
        repo.insertCity(City(2, "Test", 4))
        repo.insertCity(City(3, "Test", 4))
        verify(dao, times(2)).insert(com.nhaarman.mockitokotlin2.any())
    }

    @Test
    fun getCities() {
        `when`(dao.latest).thenReturn(
            listOf(
                City(1, "City 1", 0),
                City(2, "City 2", 2)
            )
        )
        // retrieve
        repo.getCities(object : OpenrationListener {
            override fun onSuccess(obj: Any?) {
                val list = obj as List<City>
                assertFalse(list.isNullOrEmpty())
                assertEquals(list.size, 2)
                print("Get list size ${list.size}")
            }

            override fun onError(obj: Any?) {
                fail()
            }

        })
    }
}