package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(val tripId: Long, var currency: String, var title: String, var amount: Float) {

    @PrimaryKey(autoGenerate = true)
    var eid: Long = 0
}