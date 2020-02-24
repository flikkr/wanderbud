package com.kazymir.tripweaver.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class TripsWithExpenses(
    @Embedded val trip: Trip,
    @Relation(
        parentColumn = "tid",
        entityColumn = "tripId"
    )
    val expenses: List<Expense>) {

}
