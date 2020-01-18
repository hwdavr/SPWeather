package com.demo.weather.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.demo.weather.model.api.SearchCityService
import com.demo.weather.model.city.City
import com.demo.weather.model.dao.CityDao
import com.demo.weather.model.repository.QueryCityRepo
import com.demo.weather.model.repository.RecentCityRepo
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.demo.weather.model.util.OpenrationListener
import com.nhaarman.mockitokotlin2.doAnswer
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.*


@RunWith(RobolectricTestRunner::class)
class HomeScreenViewModelTest {
    var storageRepo = Mockito.mock(RecentCityRepo::class.java)
    val queryRepo = Mockito.mock(QueryCityRepo::class.java)
    val viewModel = HomeScreenViewModel(storageRepo, queryRepo)

    @Test
    fun loadCityList() {
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[0] as OpenrationListener
            listener.onSuccess(
                listOf(
                    City("City 1", 0),
                    City("City 2", 2)
                )
            )
        }.`when`(storageRepo).getCities(com.nhaarman.mockitokotlin2.any())

        viewModel.loadCityList()
        assertTrue(viewModel.cities.value?.size ?: 0 > 0)
        print("Get list size ${viewModel.cities.value?.size}")
    }

    @Test
    fun queryCityList() {
        doAnswer { invocationOnMock ->
            val listener = invocationOnMock.arguments[1] as OpenrationListener
            listener.onSuccess(
                listOf(
                    City("City 1", 0),
                    City("City 2", 2)
                )
            )
        }.`when`(queryRepo).queryCities(ArgumentMatchers.anyString(), com.nhaarman.mockitokotlin2.any())

        viewModel.queryCityList("Test")
        assertTrue(viewModel.cities.value?.size ?: 0 > 0)
        print("Get list size ${viewModel.cities.value?.size}")
    }
}