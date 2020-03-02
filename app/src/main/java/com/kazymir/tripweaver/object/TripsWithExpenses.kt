package com.kazymir.tripweaver.`object`

import androidx.room.Embedded
import androidx.room.Relation
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.Trip

data class TripsWithExpenses(
    @Embedded val trip: Trip,
    @Relation(
        parentColumn = "tid",
        entityColumn = "tripId"
    )
    val expenses: List<Expense>) {

}
