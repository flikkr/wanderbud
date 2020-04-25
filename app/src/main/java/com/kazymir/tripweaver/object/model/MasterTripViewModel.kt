package com.kazymir.tripweaver.`object`.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.MasterTripsWithTrips
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.MasterTripRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class MasterTripViewModel(application: Application): AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: MasterTripRepository
    val allMasterTrips: LiveData<List<MasterTrip>>
    var allTrips: LiveData<List<MasterTripsWithTrips>>? = null

    init {
        val masterTripDao = AppDatabase.getDatabase(application, viewModelScope).masterTripDao()
        repository = MasterTripRepository(masterTripDao)
        allMasterTrips = repository.allMasterTrips
        allTrips = repository.getAllTripsByMasterTrip()
    }

//    fun getAllTrips() = viewModelScope.launch {
//        if (allTrips == null) allTrips = repository.getAllTripsByMasterTrip()
//        return allTrips
//    }

//    fun getMasterTrip(mid: Long): LiveData<MasterTrip> = repository.getMasterTrip(mid)

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