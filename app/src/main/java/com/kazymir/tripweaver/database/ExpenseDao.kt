package com.kazymir.tripweaver.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.kazymir.tripweaver.`object`.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense WHERE eid = :eid")
    fun getExpense(eid: Int): Expense

    @Delete
    fun delete(expense: Expense)
}