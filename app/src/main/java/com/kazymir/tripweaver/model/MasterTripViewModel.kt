package com.kazymir.tripweaver.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.MasterTripRepository
import com.kazymir.tripweaver.database.repo.TripRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class MasterTripViewModel(application: Application): AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: MasterTripRepository
    // LiveData gives us updated words when they change.
    val allTrips: LiveData<List<MasterTrip>>

    init {
        // Gets reference to TripDao from AppDatabase to construct
        // the correct TripRepository.
        val masterTripDao = AppDatabase.getDatabase(application, viewModelScope).masterTripDao()
        repository = MasterTripRepository(masterTripDao)
        allTrips = repository.allTrips
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(masterTrip: MasterTrip) = viewModelScope.launch {
        repository.insert(masterTrip)
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