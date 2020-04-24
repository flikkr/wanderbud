package com.kazymir.tripweaver.`object`.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.database.AppDatabase
import com.kazymir.tripweaver.database.repo.ExpenseRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Class extends AndroidViewModel and requires application as a parameter.
class ExpenseViewModel(application: Application): AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ExpenseRepository

    init {
        val expenseDao = AppDatabase.getDatabase(application, viewModelScope).expenseDao()
        repository = ExpenseRepository(expenseDao)
    }

    fun getLiveDataExpensesByTripId(tripId: Long): LiveData<List<Expense>> = repository.getLiveDataExpensesByTripId(tripId)

    fun getExpensesByTripId(tripId: Long): List<Expense> = runBlocking(IO) {
        repository.getExpensesByTripId(tripId)
    }

    fun insert(expense: Expense) = viewModelScope.launch {
        repository.insert(expense)
    }

    fun update(expense: Expense) = viewModelScope.launch {
        repository.update(expense)
    }

    fun delete(expense: Expense) = viewModelScope.launch {
        repository.delete(expense)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

}