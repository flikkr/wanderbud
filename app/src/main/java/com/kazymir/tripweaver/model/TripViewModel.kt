package com.kazymir.tripweaver.model

import android.app.Application
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
    private var allTrips: LiveData<List<Trip>>? = null

    init {
        // Gets reference to TripDao from AppDatabase to construct
        // the correct TripRepository.
        val tripsDao = AppDatabase.getDatabase(application, viewModelScope).tripDao()
        repository = TripRepository(tripsDao)
    }

    fun getTripsByMasterTripId(mTripId: Long): LiveData<List<Trip>>? {
        allTrips?.let { return it }
        allTrips = repository.allTrips
        return allTrips
    }

    fun insert(trip: Trip) = viewModelScope.launch {
        repository.insert(trip)
    }

    fun update(trip: Trip) = viewModelScope.launch {
        repository.update(trip)
    }

    fun delete(trip: Trip) = viewModelScope.launch {
        repository.delete(trip)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

}