package com.kazymir.tripweaver.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.TripRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class TripViewModel(application: Application): AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: TripRepository
    // LiveData gives us updated words when they change.
    val allTrips: LiveData<List<Trip>>

    init {
        // Gets reference to TripDao from AppDatabase to construct
        // the correct TripRepository.
        val tripsDao = AppDatabase.getDatabase(application, viewModelScope).tripDao()
        repository = TripRepository(tripsDao)
        allTrips = repository.allTrips
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(trip: Trip) = viewModelScope.launch {
        repository.insert(trip)
    }

//    fun update(trip: Trip) {
//        tripRepository.update(trip)
//    }
//
//    fun delete(trip: Trip) {
//        tripRepository.delete(trip)
//    }
//
//    fun clear() {
//        tripRepository.clear()
//    }

}