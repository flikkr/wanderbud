package com.kazymir.tripweaver.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kazymir.tripweaver.`object`.Trip

@Dao
interface LocationDao {
    @Query("SELECT * FROM trip")
    fun getAll(): List<Trip>

    @Query("SELECT * FROM trip WHERE tid = :tid")
    fun getTrip(tid: Integer): Trip

    @Delete
    fun delete(trip: Trip)
}