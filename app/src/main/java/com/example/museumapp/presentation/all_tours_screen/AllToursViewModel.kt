package com.example.museumapp.presentation.all_tours_screen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.domain.model.Tour
import com.example.museumapp.domain.repository.ToursRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class AllToursViewModel @Inject constructor(
    private val repository: ToursRepository
): ViewModel() {


    fun getToursFromRemote(): Tour? {
        val toursList = GlobalScope.launch(Dispatchers.IO) {
            if (repository.getToursFromRemote() != null) {
                val tList = withContext(Dispatchers.Default) {
                    repository.getToursFromRemote()!!
                    //Log.e("VIEWMODELCHECK3", toursList.toString())
                }
            }
        }

        return tList
    }



    fun getToursFromLocal(): Flow<List<Tour>?> {
        return repository.getToursFromLocal()
    }

    suspend fun getTourByTitle(title: String): Tour? {
        return repository.getTourByTitle(title)
    }

    suspend fun insertTour(tour: Tour) {
        repository.insertTour(tour)
    }

    suspend fun deleteTour(tour: Tour) {
        repository.deleteTour(tour)
    }

    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }

    fun checkInternetAccess(context: Context): Boolean {
        return context.isConnectedToWifiOrCellularDataButNoInternet()
    }

    private fun Context.isConnectedToWifiOrCellularDataButNoInternet(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return ((networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
                || networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true)
                && !isOnline())
    }

    private fun isOnline(): Boolean {
        return try {
            val url = URL("http://clients3.google.com/generate_204")
            val connection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", "Android")
            connection.setRequestProperty("Connection", "close")
            connection.connectTimeout = 1500
            connection.connect()
            connection.responseCode == 204
        } catch (e: IOException) {
            false
        }
    }


}


