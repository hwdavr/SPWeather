package com.demo.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.weather.model.City
import com.demo.weather.model.CityDataSource
import com.demo.weather.model.util.OpenrationListener

class HomeScreenViewModel(private val repository: CityDataSource): ViewModel() {

    val cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = emptyList() }

    fun loadCityList() {
        repository.getCities(object : OpenrationListener{
            override fun onSuccess(obj: Any?) {
                if (obj != null && obj is List<*>) {
                    cities.value = obj as List<City>
                }
            }

            override fun onError(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}