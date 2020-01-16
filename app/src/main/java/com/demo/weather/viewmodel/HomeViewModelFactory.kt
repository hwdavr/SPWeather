package com.demo.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.model.CityDataSource

class HomeViewModelFactory(private val respository: CityDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(respository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}