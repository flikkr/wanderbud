package com.kazymir.tripweaver.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

// Generic template for (C)RUD operations on entities
interface BaseDao<T> {

    @Insert
    suspend fun insert(obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)

}