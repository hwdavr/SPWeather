package com.demo.weather.model.repository

import com.demo.weather.model.api.SearchCityService
import com.demo.weather.model.city.City
import com.demo.weather.model.util.OpenrationListener
import org.mockito.Mockito.*
import kotlinx.coroutines.MainScope
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class QueryCityRepoTest {
    val service = mock(SearchCityService::class.java)
    var repo = QueryCityRepo(MainScope(), service)

    @Test
    fun queryCities() {
        `when`(service.queryCities(com.nhaarman.mockitokotlin2.any())).thenReturn(
            listOf(
                City("City 1", 0),
                City("City 2", 2)
            )
        )
        repo.queryCities("test 1", object : OpenrationListener {
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