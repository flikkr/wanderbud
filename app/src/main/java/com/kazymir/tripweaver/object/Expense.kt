package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(var tripId: Long, @ColumnInfo var currency: String, @ColumnInfo var title: String, @ColumnInfo var amount: Float) {

    @PrimaryKey(autoGenerate = true)
    var eid: Long = 0
}