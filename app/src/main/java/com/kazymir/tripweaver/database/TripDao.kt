package com.kazymir.tripweaver.database

import androidx.room.*
import com.kazymir.tripweaver.`object`.Trip

@Dao
interface TripDao {
    @Query("SELECT * FROM trip")
    fun getAll(): List<Trip>

    @Query("SELECT * FROM trip WHERE tid = :tid")
    fun getTrip(tid: Integer): Trip

    @Transaction
    @Query("SELECT * FROM trip")
    fun getTripsWithExpenses(): List<TripsWithExpenses>

    @Insert
    fun addTrip(trip: Trip)

    @Update
    fun updateTrip(trip: Trip)

    @Delete
    fun delete(trip: Trip)
}