package com.kazymir.tripweaver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.TripWithExpenses

@Dao
interface TripDao: BaseDao<Trip> {

    @Query("SELECT * FROM Trip WHERE mTripId = :mTripId")
    fun getTripsByMasterTripId(mTripId: Long): LiveData<List<Trip>>

    @Transaction
    @Query("SELECT * FROM Trip WHERE tid = :tid")
    fun getExpensesByTripId(tid: Long): LiveData<TripWithExpenses>

    @Query("SELECT * FROM Trip WHERE tid = :tid")
    fun getLiveDateTrip(tid: Long): LiveData<Trip>

    @Query("SELECT * FROM Trip WHERE tid = :tid")
    fun getTrip(tid: Long): Trip

    @Query("DELETE FROM Trip")
    suspend fun clear()
}