package com.example.mvvmdemo.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.entity.City

class CitiyRepository {

    private fun getValues(): List<City> = listOf(
            City("Chicago"),
            City("New York"),
            City("Egypte"),
            City("Moscow"),
            City("Paris"),
            City("Tangier")
        )

    fun getCities(): MutableLiveData<List<City>> {
        val output = MutableLiveData<List<City>>()
        output.value = getValues()
        return output
    }
}