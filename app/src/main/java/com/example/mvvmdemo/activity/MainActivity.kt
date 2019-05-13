package com.example.mvvmdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.CityRecyclerAdapter
import com.example.mvvmdemo.entity.City
import com.example.mvvmdemo.viewmodel.CitiesListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var cityRecyclerAdapter: CityRecyclerAdapter
    lateinit var citiesListViewModel: CitiesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citiesListViewModel = ViewModelProviders.
            of(this).
            get(CitiesListViewModel::class.java)

        citiesListViewModel.getCities().observe(this, Observer<List<City>> {
            cities -> cityRecyclerAdapter.updateCities(cities)
        })

        citiesListViewModel.getIsLoading().observe(this, Observer<Boolean> {
                isLoading -> toggleProgressBar(isLoading)

        })

        addCityButton.setOnClickListener {
            citiesListViewModel.addCity(City("Agadir"))
        }
        initCitiesRecycleView()
    }

    fun initCitiesRecycleView() {
        cityRecyclerAdapter = CityRecyclerAdapter(citiesListViewModel.getCities().value!!)
        citiesRecyclerView.layoutManager = LinearLayoutManager(this)
        citiesRecyclerView.adapter = cityRecyclerAdapter
    }

    fun toggleProgressBar (showProgress: Boolean) {
        val newVisibility = if(showProgress) View.VISIBLE else View.GONE
        citiesProgressBar.visibility = newVisibility
    }
}
