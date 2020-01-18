package com.demo.weather.view

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weather.R
import com.demo.weather.model.city.City
import com.demo.weather.model.dao.CityDao
import com.demo.weather.view.customview.debounce
import com.demo.weather.view.customview.onTextChanged
import com.demo.weather.viewmodel.HomeScreenViewModel
import com.talengineer.magicmarker.customview.DividerItemDecorator
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu, menu)

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        val searchView = searchItem?.actionView as? SearchView
        val searchInfo = searchManager?.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchInfo)

        GlobalScope.launch(Dispatchers.Main) {
            // Use Coroutine Channel for SearchView Text Change
            searchView?.onTextChanged()?.debounce(300)?.consumeEach {
                    Log.d(TAG, "Detect text changed after debouncing, value: $it")
                    viewModel.queryCityList(it)
                }
        }

        // Listen to the search item is expanded or collapse
        searchItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                setItemsVisibility(menu, null, false)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                setItemsVisibility(menu, null, true)
                return true
            }
        })

        return true
    }

    private fun setItemsVisibility(menu: Menu, exception: MenuItem?, visible: Boolean) {
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            if (item !== exception) item.isVisible = visible
        }
    }

}
