package com.demo.weather.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.demo.weather.R
import com.demo.weather.model.apidata.entity.CurrentCondition
import com.demo.weather.model.city.City
import com.demo.weather.model.util.CITY_ID
import com.demo.weather.viewmodel.CityWeatherViewModel
import com.demo.weather.viewmodel.HomeScreenViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.list_item_city.*
import kotlinx.android.synthetic.main.list_item_city.city_name
import javax.inject.Inject

class CityWeatherActivity : AppCompatActivity() {
    private val TAG = CityWeatherActivity::class.java.simpleName
    private lateinit var viewModel: CityWeatherViewModel
    private var city: String? = null
    private lateinit var context: Context

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        context = this
        city = intent.getStringExtra(CITY_ID)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CityWeatherViewModel::class.java)

        viewModel.weather.observe(this, Observer<CurrentCondition> {
            Log.d(TAG, "Weather updated")
            city_name.text = city
            temperature.text = it.temp_C
            humidity.text = "Humidity: ${it.humidity}"
            updated_time.text = "Last updated: ${it.observation_time}"
            weather_desc.text = it.weatherDesc
            Glide.with(context).load(it.weatherIconUrl).into(weather_icon)
        })
    }

    override fun onResume() {
        super.onResume()
        if (city != null) {
            viewModel.checkCurrentWeather(city!!)
        } else {
            Log.e(TAG, "Cannot get city name")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}