package com.demo.weather.di

import androidx.annotation.NonNull
import com.demo.weather.model.api.LocalWeatherService
import com.demo.weather.model.api.SearchCityService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideSearchService(): SearchCityService {
        return SearchCityService()
    }


    @Provides
    @Singleton
    fun provideCityWeatherPepo(): LocalWeatherService {
        return LocalWeatherService()
    }
}