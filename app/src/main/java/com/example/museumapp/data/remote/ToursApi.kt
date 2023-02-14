package com.example.museumapp.data.remote

import com.example.museumapp.domain.model.Tour
import com.example.museumapp.util.Constants.API_URL
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ToursApi @Inject constructor() {

    fun getTour(): List<Tour>? {
        val response = Fuel.get(API_URL).responseString()

        val tour = if (response.second.statusCode == 200) {
            val content = response.third.get()
            try {
                Gson().fromJson(content, object : TypeToken<List<Tour>>() {}.type)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
        return tour
    }
}

