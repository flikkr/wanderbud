package com.kazymir.tripweaver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.database.dao.BaseDao

@Dao
interface ExpenseDao: BaseDao<Expense> {

    @Query("SELECT * FROM expense WHERE eid = :eid")
    fun getExpense(eid: Long): Expense

    @Query("SELECT * FROM expense WHERE tripId = :tripId ORDER BY created DESC")
    fun getLiveDataExpensesByTripId(tripId: Long): LiveData<List<Expense>>

    @Query("SELECT * FROM expense WHERE tripId = :tripId")
    fun getExpensesByTripId(tripId: Long): List<Expense>

    @Query("SELECT SUM(amount) FROM expense WHERE tripId = :tripId")
    suspend fun getTotal(tripId: Long): Float?

    @Query("DELETE FROM Expense")
    suspend fun clear()
}