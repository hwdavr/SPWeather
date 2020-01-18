package com.demo.weather.di

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
}