package com.demo.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weather.R
import com.demo.weather.model.city.City
import com.demo.weather.model.dao.CityDao
import com.demo.weather.model.repository.RecentCityRepo
import com.demo.weather.viewmodel.HomeScreenViewModel
import com.demo.weather.viewmodel.ViewModelFactory
import com.talengineer.magicmarker.customview.DividerItemDecorator
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class HomeScreenActivity : AppCompatActivity() {
    private val TAG = HomeScreenActivity::class.java.simpleName

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter

    @Inject
    lateinit var dao: CityDao

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HomeScreenViewModel::class.java)

        viewModel.cities.observe(this, Observer<List<City>> {
            Log.d(TAG, "List updated")
            adapter.updateData(it)
        })
    }

    private fun initUI() {
        adapter = HomeScreenAdapter(viewModel.cities.value ?: emptyList())
        recycler_view.layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.divider_default)!!)
        recycler_view.addItemDecoration(itemDecorator)
        recycler_view.adapter = adapter
    }
}
