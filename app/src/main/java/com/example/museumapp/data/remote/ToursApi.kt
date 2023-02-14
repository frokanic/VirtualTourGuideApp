package com.example.museumapp.data.remote

import com.example.museumapp.util.Constants.API_URL
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf
import javax.inject.Inject

class ToursApi @Inject constructor() {

    suspend fun getTour(): ResponseResultOf<String> {
        return Fuel.get(API_URL).responseString()
    }
}

