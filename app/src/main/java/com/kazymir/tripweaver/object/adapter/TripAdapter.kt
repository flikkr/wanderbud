package com.kazymir.tripweaver.`object`.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.fragment.MasterTripFragmentDirections
import kotlinx.android.synthetic.main.item_master_trip.view.*
import kotlinx.android.synthetic.main.item_master_trip.view.trip_title
import kotlinx.android.synthetic.main.item_trip.view.*


class TripAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<TripAdapter.TripHolder>() {

    private var trips: List<Trip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip, parent, false)
        return TripHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: TripHolder, position: Int) {
        val currentTrip = trips[position]
        holder.tripId = currentTrip.tid
        holder.title.text = currentTrip.title
        holder.country.text = currentTrip.destination
    }

    override fun getItemCount(): Int = trips.size

    fun setTrips(trips: List<Trip>) {
        this.trips = trips
        notifyDataSetChanged()
    }

    class TripHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var view: View = itemView
        var tripId: Long = 0
        val title: TextView = itemView.trip_title
        val country: TextView = itemView.country

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(itemView: View) {
            val action = MasterTripFragmentDirections.actionMasterTripFragmentToTripFragment(tripId)
            itemView.findNavController().navigate(action)
        }
    }
}