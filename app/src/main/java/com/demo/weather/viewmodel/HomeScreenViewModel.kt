package com.demo.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.weather.model.City

class HomeScreenViewModel: ViewModel() {

    val cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = emptyList() }

}