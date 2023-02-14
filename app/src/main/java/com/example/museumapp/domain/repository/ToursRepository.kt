package com.example.museumapp.domain.repository

import com.example.museumapp.domain.model.Tour
import kotlinx.coroutines.flow.Flow

interface ToursRepository {

    fun getToursFromRemote(): List<Tour>?

    fun getToursFromLocal(): Flow<List<Tour>?>

    suspend fun getTourByTitle(title: String): Tour?

    suspend fun insertTour(tour: Tour)

    suspend fun deleteTour(tour: Tour)

}