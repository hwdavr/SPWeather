package com.demo.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.demo.weather.R
import com.demo.weather.model.city.City
import com.demo.weather.model.city.RecentCityRepo
import com.demo.weather.model.database.AppDatabase
import com.demo.weather.viewmodel.HomeScreenViewModel
import com.demo.weather.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class HomeScreenActivity : AppCompatActivity() {
    private val TAG = HomeScreenActivity::class.java.simpleName

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCityList()
    }

    private fun initViewModel() {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "cities")
                    .build()
        val dao = db.cityDao()
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory(RecentCityRepo(dao)))
            .get(HomeScreenViewModel::class.java)

        viewModel.cities.observe(this, Observer<List<City>> {
            Log.d(TAG, "List updated")
            adapter.updateData(it)
        })
    }

    private fun initUI() {
        adapter = HomeScreenAdapter(viewModel.cities.value ?: emptyList())
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }


}
