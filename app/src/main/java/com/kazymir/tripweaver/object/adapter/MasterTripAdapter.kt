package com.kazymir.tripweaver.`object`.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.model.MasterTripViewModel
import com.kazymir.tripweaver.fragment.AllTripsFragmentDirections
import kotlinx.android.synthetic.main.item_master_trip.view.*

/**
 * Recycler view adapter for master trips
 */
class MasterTripAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<MasterTripAdapter.MasterTripHolder>() {

    private var masterTrips: MutableList<MasterTrip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterTripHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_master_trip, parent, false)

        return MasterTripHolder(itemView)
    }

    // Binds viewholder attributes
    override fun onBindViewHolder(holderMaster: MasterTripHolder, position: Int) {
        val currentTrip = masterTrips[position]
        holderMaster.mTripId = currentTrip.mid
        holderMaster.tripTitle.text = currentTrip.title
        holderMaster.tripStart.text = currentTrip.startDate
        holderMaster.tripEnd.text = currentTrip.endDate
    }

    // Returns the number of items in list
    override fun getItemCount(): Int = masterTrips.size

    // Sets list items
    fun setTrips(masterTrips: List<MasterTrip>) {
        this.masterTrips = masterTrips  as MutableList<MasterTrip>
        notifyDataSetChanged()
    }

    // Remove list item
    fun removeItem(viewHolder: RecyclerView.ViewHolder, viewModel: MasterTripViewModel) {
        viewModel.delete(masterTrips[viewHolder.adapterPosition])
        masterTrips.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    class MasterTripHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var view: View = itemView
        var mTripId: Long = 0
        val tripTitle: TextView = itemView.trip_title
        val tripStart: TextView = itemView.start_date
        val tripEnd: TextView = itemView.end_date

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(itemView: View) {
            // Navigate to master trip fragment
            val action = AllTripsFragmentDirections.actionNavAllTripsToMasterTripFragment(
                mTripId,
                tripTitle.text.toString()
            )
            itemView.findNavController().navigate(action)
        }
    }
}