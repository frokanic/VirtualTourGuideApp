package com.example.museumapp.data.remote

import android.util.Log
import com.example.museumapp.domain.model.Tour
import com.example.museumapp.util.Constants.API_URL
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class ToursApi @Inject constructor() {

    suspend fun getTour(): ResponseResultOf<String> {
        Log.e("APICHECK", Fuel.get(API_URL).responseString().toString())
        return Fuel.get(API_URL).responseString()
    }
}

