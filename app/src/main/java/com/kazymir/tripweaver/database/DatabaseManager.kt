package com.kazymir.tripweaver.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazymir.tripweaver.`object`.Trip

@Database(entities = arrayOf(Trip::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}