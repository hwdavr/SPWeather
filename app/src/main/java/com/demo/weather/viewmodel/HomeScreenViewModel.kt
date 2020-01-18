package com.demo.weather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.weather.model.city.City
import com.demo.weather.model.city.CityDataSource
import com.demo.weather.model.util.OpenrationListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject
constructor(private val repository: CityDataSource): ViewModel() {
    private val TAG = HomeScreenViewModel::class.java.simpleName
    val cities: MutableLiveData<List<City>> = MutableLiveData<List<City>>().apply { value = emptyList() }

    init {
        Log.d(TAG, "Injection discovered")
    }

    fun loadCityList() {
        repository.getCities(object : OpenrationListener{
            override fun onSuccess(obj: Any?) {
                if (obj != null && obj is List<*>) {
                    GlobalScope.launch(Dispatchers.Main) {
                        cities.value = obj as List<City>
                    }
                }
            }

            override fun onError(obj: Any?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}