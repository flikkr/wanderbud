package com.kazymir.tripweaver.`object`

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MasterTrip(var title: String, var startDate: String, var endDate: String?) {
    @PrimaryKey(autoGenerate = true)
    var mid: Long = 0

}