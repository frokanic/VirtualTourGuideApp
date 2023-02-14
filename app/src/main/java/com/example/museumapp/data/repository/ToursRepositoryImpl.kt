package com.example.museumapp.data.repository

import com.example.museumapp.data.local.ToursDao
import com.example.museumapp.data.remote.ToursApi
import com.example.museumapp.domain.model.Tour
import com.example.museumapp.domain.repository.ToursRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToursRepositoryImpl @Inject constructor(
    private val api: ToursApi,
    private val dao: ToursDao
): ToursRepository {

    override fun getToursFromRemote(): List<Tour>? {
        return api.getTour()
    }

    override fun getToursFromLocal(): Flow<List<Tour>?> {
        return dao.getTours()
    }

    override suspend fun getTourByTitle(title: String): Tour? {
        return dao.getTourByTitle(title)
    }

    override suspend fun insertTour(tour: Tour) {
        dao.insertTour(tour)
    }

    override suspend fun deleteTour(tour: Tour) {
        dao.deleteTour(tour)
    }

}