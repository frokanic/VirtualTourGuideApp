package com.example.museumapp.data.local

import androidx.room.*
import com.example.museumapp.domain.model.Tour
import kotlinx.coroutines.flow.Flow

@Dao
interface ToursDao {

    @Query("SELECT * FROM tours")
    fun getTours(): Flow<List<Tour>?>

    @Query("SELECT * FROM tours WHERE title = :title")
    suspend fun getTourByTitle(title: String): Tour?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTour(tour: Tour)

    @Delete
    suspend fun deleteTour(tour: Tour)

}