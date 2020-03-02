package com.kazymir.tripweaver.database.repo

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.dao.MasterTripDao
import com.kazymir.tripweaver.database.dao.TripDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MasterTripRepository(private val masterTripDao: MasterTripDao) {
    val allTrips: LiveData<List<MasterTrip>> = masterTripDao.getAll()

    suspend fun insert(masterTrip: MasterTrip) {
        masterTripDao.insert(masterTrip)
    }

//    fun update(trip: Trip) {
//        CoroutineScope(IO).launch {
//            tripDao.update(trip)
//        }
//    }
//
//    fun delete(trip: Trip) {
//        CoroutineScope(IO).launch {
//            tripDao.delete(trip)
//        }
//    }
//
//    fun clear() {
//        CoroutineScope(IO).launch {
//            tripDao.clear()
//        }
//    }
//
//    fun getAll(): LiveData<List<Trip>> {
//        lateinit var all: LiveData<List<Trip>>
//        CoroutineScope(IO).launch {
//            all = tripDao.getAll()
//        }
//        return all
//    }
}