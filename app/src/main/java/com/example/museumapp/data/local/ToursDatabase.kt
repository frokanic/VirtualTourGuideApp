package com.example.museumapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.museumapp.domain.model.Tour

@Database(
    entities = [Tour::class],
    version = 1
)
abstract class ToursDatabase: RoomDatabase() {

    abstract val toursDao: ToursDao

    companion object {
        const val DATABASE_NAME = "tours_db"
    }

}