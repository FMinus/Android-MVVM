package com.example.mvvmdemo.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.entity.City
import com.example.mvvmdemo.repository.CitiyRepository

class CitiesListViewModel(): ViewModel() {

    var citiesLiveData: MutableLiveData<List<City>>
    var loadingLiveData: MutableLiveData<Boolean>

    init {
        val repository = CitiyRepository()
        loadingLiveData = MutableLiveData()
        loadingLiveData.value = false
        citiesLiveData = repository.getCities()
    }

    fun getCities(): LiveData<List<City>> = citiesLiveData

    fun getIsLoading():LiveData<Boolean> = loadingLiveData

    fun addCity(city: City) {
        loadingLiveData.postValue(true)
        Handler().postDelayed({
            val currentCities = citiesLiveData.value?.toMutableList()
            currentCities?.add(city)
            citiesLiveData.postValue(currentCities)
            loadingLiveData.postValue(false)
        }, 1000)
    }
}