package com.example.museumapp.network

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }

}