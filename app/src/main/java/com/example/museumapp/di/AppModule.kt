package com.example.museumapp.di

import android.app.Application
import androidx.room.Room
import com.example.museumapp.data.local.ToursDatabase
import com.example.museumapp.data.remote.ToursApi
import com.example.museumapp.data.repository.ToursRepositoryImpl
import com.example.museumapp.domain.model.Tour
import com.example.museumapp.domain.repository.ToursRepository
import com.example.museumapp.util.Constants
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    suspend fun provideToursApi(api: ToursApi): ResponseResultOf<String> {
        return api.getTour()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): ToursDatabase {
        return Room.databaseBuilder(
            app,
            ToursDatabase::class.java,
            ToursDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideToursRepository(api: ToursApi, db: ToursDatabase): ToursRepository {
        return ToursRepositoryImpl(api, db.toursDao)
    }

}
