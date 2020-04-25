package com.kazymir.tripweaver.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.Location
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.database.dao.ExpenseDao
import com.kazymir.tripweaver.database.dao.LocationDao
import com.kazymir.tripweaver.database.dao.MasterTripDao
import com.kazymir.tripweaver.database.dao.TripDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@Database(
    entities = [MasterTrip::class, Trip::class, Location::class, Expense::class],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun masterTripDao(): MasterTripDao
    abstract fun tripDao(): TripDao
    abstract fun locationDao(): LocationDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tripweaver_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}