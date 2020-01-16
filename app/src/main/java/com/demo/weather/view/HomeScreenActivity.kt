package com.demo.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weather.R
import com.demo.weather.viewmodel.HomeScreenViewModel
import com.demo.weather.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initUI()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory())
            .get(HomeScreenViewModel::class.java)

    }

    private fun initUI() {
        adapter = HomeScreenAdapter(viewModel.cities.value ?: emptyList())
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }


}
