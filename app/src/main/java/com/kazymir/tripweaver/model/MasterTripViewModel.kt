package com.kazymir.tripweaver.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.MasterTripRepository
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
        allTrips = repository.allMasterTrips
    }

    fun insert(mTrip: MasterTrip) = viewModelScope.launch {
        repository.insert(mTrip)
    }

    fun update(mTrip: MasterTrip) = viewModelScope.launch {
        repository.update(mTrip)
    }

    fun delete(mTrip: MasterTrip) = viewModelScope.launch {
        repository.delete(mTrip)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

}