package com.kazymir.tripweaver.database.repo

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.TripsWithExpenses
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.dao.TripDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class TripRepository(private val tripDao: TripDao) {
    var allTrips: LiveData<List<Trip>>? = null

    fun getTripsByMasterTripId(mTripId: Long): LiveData<List<Trip>> {
        if (allTrips == null) tripDao.getTripsByMasterTripId(mTripId)
        return allTrips!!
    }

    suspend fun insert(trip: Trip) {
        tripDao.insert(trip)
    }

    suspend fun update(mTrip: Trip) {
        tripDao.update(mTrip)
    }

    suspend fun delete(mTrip: Trip) {
        tripDao.delete(mTrip)
    }

    suspend fun clear() {
        tripDao.clear()
    }
}