package com.kazymir.tripweaver.database.repo

import androidx.lifecycle.LiveData
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.TripWithExpenses
import com.kazymir.tripweaver.database.dao.TripDao


class TripRepository(private val tripDao: TripDao) {
    private var allTrips: LiveData<List<Trip>>? = null

    fun getTripsByMasterTripId(mTripId: Long): LiveData<List<Trip>>? {
        if (allTrips == null) allTrips = tripDao.getTripsByMasterTripId(mTripId)
        return allTrips
    }

    fun getTripWithExpenses(tripId: Long): LiveData<TripWithExpenses> = tripDao.getExpensesByTripId(tripId)

    fun getLiveDataTrip(tripId: Long): LiveData<Trip> = tripDao.getLiveDateTrip(tripId)

    fun getTrip(tripId: Long): Trip = tripDao.getTrip(tripId)

    suspend fun insert(trip: Trip) = tripDao.insert(trip)

    suspend fun update(trip: Trip) = tripDao.update(trip)

    suspend fun delete(trip: Trip) = tripDao.delete(trip)

    suspend fun clear() = tripDao.clear()
}