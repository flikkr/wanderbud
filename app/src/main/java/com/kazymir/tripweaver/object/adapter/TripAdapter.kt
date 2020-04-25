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
import com.kazymir.tripweaver.`object`.model.TripViewModel
import com.kazymir.tripweaver.fragment.MasterTripFragmentDirections
import kotlinx.android.synthetic.main.item_master_trip.view.*
import kotlinx.android.synthetic.main.item_master_trip.view.trip_title
import kotlinx.android.synthetic.main.item_trip.view.*

/**
 * Recycler view adapter for trips
 */
class TripAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<TripAdapter.TripHolder>() {

    private var trips: MutableList<Trip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip, parent, false)
        return TripHolder(
            itemView
        )
    }

    // Binds viewholder attributes
    override fun onBindViewHolder(holder: TripHolder, position: Int) {
        val currentTrip = trips[position]
        holder.tripId = currentTrip.tid
        holder.title.text = currentTrip.title
        holder.country.text = currentTrip.destination
    }

    // Returns the number of items in list
    override fun getItemCount(): Int = trips.size

    // Sets list items
    fun setTrips(trips: List<Trip>) {
        this.trips = trips as MutableList<Trip>
        notifyDataSetChanged()
    }

    // Remove list item
    fun removeItem(viewHolder: RecyclerView.ViewHolder, viewModel: TripViewModel) {
        viewModel.delete(trips[viewHolder.adapterPosition])
        trips.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
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
            // Navigate to trip fragment
            val action = MasterTripFragmentDirections.actionMasterTripFragmentToTripFragment(tripId, title.text.toString())
            itemView.findNavController().navigate(action)
        }
    }
}