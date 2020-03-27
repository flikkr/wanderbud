package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Trip(val mTripId: Long, var title: String, var destination: String) {
    @PrimaryKey(autoGenerate = true)
    var tid: Long = 0

    var tBudget: Float = 0f

    var cBudget: Float = 0f
}