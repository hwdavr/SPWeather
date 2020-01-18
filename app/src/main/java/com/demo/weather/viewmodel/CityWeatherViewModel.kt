package com.demo.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.weather.model.apidata.entity.CurrentCondition
import com.demo.weather.model.city.City
import com.demo.weather.model.repository.CityWeatherRepo
import com.demo.weather.model.util.OpenrationListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class CityWeatherViewModel  @Inject
constructor(
    private val repository: CityWeatherRepo
): ViewModel() {
    private val TAG = CityWeatherViewModel::class.java.simpleName
    val weather: MutableLiveData<CurrentCondition> = MutableLiveData<CurrentCondition>().apply { CurrentCondition() }

    init {
        Log.d(TAG, "Injection discovered")
    }

    fun checkCurrentWeather(city: String) {
        repository.currentWeather(city, object : OpenrationListener {
            override fun onSuccess(obj: Any?) {
                if (obj != null && obj is CurrentCondition) {
                    GlobalScope.launch(Dispatchers.Main) {
                        weather.value = obj
                    }
                }
            }

            override fun onError(obj: Any?) {
            }
        })
    }
}
