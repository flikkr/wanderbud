package com.kazymir.tripweaver.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kazymir.tripweaver.`object`.Location
import com.kazymir.tripweaver.database.dao.BaseDao

@Dao
interface LocationDao: BaseDao<Location> {
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE lid = :lid")
    fun getLocation(lid: Long): Location

}