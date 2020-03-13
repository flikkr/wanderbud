package com.kazymir.tripweaver.database.repo

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.MasterTripsWithTrips
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.dao.MasterTripDao
import com.kazymir.tripweaver.database.dao.TripDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MasterTripRepository(private val masterTripDao: MasterTripDao) {
    val allMasterTrips: LiveData<List<MasterTrip>> = masterTripDao.getAll()
    var allTrips: LiveData<List<MasterTripsWithTrips>>? = null

    fun getAllTrips(mTrip: MasterTrip): LiveData<List<MasterTripsWithTrips>>? {
        if (allTrips == null) allTrips = masterTripDao.getMasterTripsWithTrips()
        return allTrips
    }

    suspend fun insert(mTrip: MasterTrip) {
        masterTripDao.insert(mTrip)
    }

    suspend fun update(mTrip: MasterTrip) {
        masterTripDao.update(mTrip)
    }

    suspend fun delete(mTrip: MasterTrip) {
        masterTripDao.delete(mTrip)
    }

    suspend fun clear() {
        masterTripDao.clear()
    }
}