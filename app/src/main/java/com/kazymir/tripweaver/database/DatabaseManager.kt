package com.kazymir.tripweaver.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.Location
import com.kazymir.tripweaver.`object`.Trip

@Database(entities = [Trip::class, Location::class, Expense::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun locationDao(): LocationDao
    abstract fun expenseDao(): ExpenseDao
}