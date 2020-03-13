package com.kazymir.tripweaver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.MasterTripsWithTrips
import com.kazymir.tripweaver.`object`.Trip

@Dao
interface MasterTripDao: BaseDao<MasterTrip> {

    @Query("SELECT * FROM MasterTrip")
    fun getAll(): LiveData<List<MasterTrip>>

    @Transaction
    @Query("SELECT * FROM MasterTrip")
    fun getMasterTripsWithTrips(): LiveData<List<MasterTripsWithTrips>>

    @Query("SELECT * FROM MasterTrip WHERE mid = :mid")
    suspend fun getTrip(mid: Long): MasterTrip

    @Query("DELETE FROM MasterTrip")
    suspend fun clear()
}