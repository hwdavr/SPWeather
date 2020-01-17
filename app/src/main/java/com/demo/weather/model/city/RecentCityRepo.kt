package com.demo.weather.model.city

import com.demo.weather.model.util.OpenrationListener
import kotlinx.coroutines.*

class RecentCityRepo(val cityDao: CityDao) : CityDataSource {
    private val job: Job = Job()
    private var scope = CoroutineScope(Dispatchers.IO + job)

    // Create another constructor to be able to test the corouine in MainScope() if doing instrumentation test
    constructor(scope: CoroutineScope, dao: CityDao): this(dao) {
        this.scope = scope
    }

    override fun insertCity(city: City) {
        scope.launch {
            runBlocking {
                val dummyList = arrayOf(
                    City(0, "Singapore", 0),
                    City(1, "Beijing", 1),
                    City(2, "London", 2)
                )
                cityDao.insertAll(*dummyList)
                cityDao.insert(city)
            }
        }
    }

    override fun getCities(listner: OpenrationListener) {
        scope.launch {
            runBlocking {
                listner.onSuccess(cityDao.latest)
            }
        }
    }


}