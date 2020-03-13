package com.kazymir.tripweaver.`object`

import androidx.room.Embedded
import androidx.room.Relation
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.Trip

data class MasterTripsWithTrips(
    @Embedded val mTrip: MasterTrip,
    @Relation(
        parentColumn = "mid",
        entityColumn = "mTripId"
    )
    val trip: List<Trip>) {

}
