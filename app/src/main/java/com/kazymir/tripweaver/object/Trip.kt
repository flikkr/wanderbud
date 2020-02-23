package com.kazymir.tripweaver.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trip(@ColumnInfo var title: String, @ColumnInfo var startDate: String, @ColumnInfo var endDate: String?, @ColumnInfo val destName: String) {
    //    val durationInDays =
    companion object {
        var count = 0
    }

    @PrimaryKey
    var tid = count++
}