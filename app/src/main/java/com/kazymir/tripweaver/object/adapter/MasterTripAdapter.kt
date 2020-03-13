package com.kazymir.tripweaver.`object`.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.fragment.AllTripsFragmentDirections
import kotlinx.android.synthetic.main.item_master_trip.view.*


class MasterTripAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<MasterTripAdapter.MasterTripHolder>() {

    private var masterTrips: List<MasterTrip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterTripHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_master_trip, parent, false)
        return MasterTripHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holderMaster: MasterTripHolder, position: Int) {
        val currentTrip = masterTrips[position]
        holderMaster.tripId = currentTrip.mid
        holderMaster.tripTitle.text = currentTrip.title
        holderMaster.tripStart.text = currentTrip.startDate
        holderMaster.tripEnd.text = currentTrip.endDate
    }

    override fun getItemCount(): Int = masterTrips.size

    fun setTrips(masterTrips: List<MasterTrip>) {
        this.masterTrips = masterTrips
        notifyDataSetChanged()
    }

    class MasterTripHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var view: View = itemView
        var tripId: Long = 0
        val tripTitle: TextView = itemView.trip_title
        val tripStart: TextView = itemView.start_date
        val tripEnd: TextView = itemView.end_date

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(itemView: View) {
            val action = AllTripsFragmentDirections.actionNavAllTripsToMasterTripFragment(tripId)
            itemView.findNavController().navigate(action)
        }
    }
}