package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Trip(@ColumnInfo var title: String, @ColumnInfo var startDate: String, @ColumnInfo var endDate: String?, @ColumnInfo val destName: String) {
    //    val durationInDays =

    @PrimaryKey(autoGenerate = true)
    var tid: Long = 0

    @ColumnInfo
    var tBudget: Float = 0f

    @ColumnInfo
    var cBudget: Float = 0f
}