package com.kazymir.tripweaver.`object`.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.TripWithExpenses
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.TripRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Class extends AndroidViewModel and requires application as a parameter.
class TripViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: TripRepository

    init {
        // Gets reference to TripDao from AppDatabase to construct
        // the correct TripRepository.
        val tripDao = AppDatabase.getDatabase(application, viewModelScope).tripDao()
        repository = TripRepository(tripDao)
    }

    fun getTripsByMasterTripId(mTripId: Long): LiveData<List<Trip>>? {
        return repository.getTripsByMasterTripId(mTripId)
    }

    fun getTripWithExpenses(tripId: Long): LiveData<TripWithExpenses> =
        repository.getTripWithExpenses(tripId)

    fun getLiveDataTrip(tripId: Long): LiveData<Trip> = repository.getLiveDataTrip(tripId)

    fun getTrip(tripId: Long): Trip = runBlocking(IO) {
        repository.getTrip(tripId)
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