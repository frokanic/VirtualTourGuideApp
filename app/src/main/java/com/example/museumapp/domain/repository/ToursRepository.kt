package com.example.museumapp.domain.repository

import com.example.museumapp.domain.model.Tour
import com.github.kittinunf.fuel.core.ResponseResultOf
import kotlinx.coroutines.flow.Flow

interface ToursRepository {

    suspend fun getTours(): ResponseResultOf<String>

    fun getToursDb(): Flow<List<Tour>?>

    suspend fun getTourByTitle(title: String): Tour?

    suspend fun insertTour(tour: Tour)

    suspend fun deleteTour(title: String)

}