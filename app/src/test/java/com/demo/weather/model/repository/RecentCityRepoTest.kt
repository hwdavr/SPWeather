package com.demo.weather.model.repository

import com.demo.weather.model.city.City
import com.demo.weather.model.dao.CityDao
import com.demo.weather.model.util.OpenrationListener
import kotlinx.coroutines.MainScope
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
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
        repo.insertCity(City("Test", 4))
        repo.insertCity(City("Test", 4))
        verify(dao, times(2)).insert(com.nhaarman.mockitokotlin2.any())
    }

    @Test
    fun getCities() {
        `when`(dao.latest).thenReturn(
            listOf(
                City("City 1", 0),
                City("City 2", 2)
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