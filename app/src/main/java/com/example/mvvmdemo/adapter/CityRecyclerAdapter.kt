package com.example.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.entity.City
import kotlinx.android.synthetic.main.city_item.view.*

class CityRecyclerAdapter(private var cities: List<City>): RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return ViewHolder(view)
    }

    fun updateCities(newCities: List<City>){
        cities = newCities;
        notifyDataSetChanged()
    }

    override fun getItemCount() = cities.size;

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCity(cities[position])
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindCity(city: City){
            view.cityName.text = city.name
        }
    }
}
