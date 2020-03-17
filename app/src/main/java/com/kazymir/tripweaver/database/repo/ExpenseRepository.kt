package com.kazymir.tripweaver.database.repo

import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.database.dao.ExpenseDao


class ExpenseRepository(private val expenseDao: ExpenseDao) {
    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    suspend fun clear() {
        expenseDao.clear()
    }
}