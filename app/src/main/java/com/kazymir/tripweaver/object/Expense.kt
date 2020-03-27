package com.kazymir.tripweaver.`object`

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Expense(
    val tripId: Long,
    var currency: String,
    var title: String,
    var amount: Float,
    var type: String
) {

    @PrimaryKey(autoGenerate = true)
    var eid: Long = 0

    // Used to access the different type of expense category. Maps to resource value of icon
    companion object {
        @Ignore
        val expenseType: SortedMap<String, Int> = sortedMapOf(
            "Food" to 1,
            "Drink" to 1,
            "Transport" to 1,
            "Accommodation" to 1,
            "Activity" to 1,
            "Entertainment" to 1,
            "Shopping" to 1,
            "Taxi" to 1,
            "Museum" to 1,
            "Other" to 1
        )
    }
}