package com.kazymir.tripweaver.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.database.dao.BaseDao

@Dao
interface ExpenseDao: BaseDao<Expense> {

    @Query("SELECT * FROM expense WHERE eid = :eid")
    fun getExpense(eid: Long): Expense

}