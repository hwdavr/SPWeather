package com.demo.weather.di

import com.demo.weather.model.api.wwo.WWOLocalWeatherService
import com.demo.weather.model.api.wwo.WWOSearchCityService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideSearchService(): WWOSearchCityService {
        return WWOSearchCityService()
    }


    @Provides
    @Singleton
    fun provideCityWeatherPepo(): WWOLocalWeatherService {
        return WWOLocalWeatherService()
    }
}