package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.MasterTripAdapter
import com.kazymir.tripweaver.model.MasterTripViewModel
import com.kazymir.tripweaver.model.TripViewModel
import kotlinx.android.synthetic.main.fragment_all_trips.view.*


class AllTripsFragment : Fragment(), View.OnClickListener, AddTripDialog.AddTripDialogListener {
    private lateinit var masterTripViewModel: MasterTripViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_all_trips, container, false)

        val fab = view.floatingAddTrip
        fab.setOnClickListener(this)

        val recyclerView: RecyclerView = view.recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = MasterTripAdapter(context!!)
        recyclerView.adapter = adapter

        masterTripViewModel = ViewModelProvider(this).get(MasterTripViewModel::class.java)
        masterTripViewModel.allTrips.observe(viewLifecycleOwner, Observer { trips ->
            // Update the cached copy of the trips in the adapter.
            trips?.let { adapter.setTrips(it) }
        })

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.floatingAddTrip -> openDialog()
        }
    }

    private fun openDialog() {
        val addTripDialog = AddTripDialog()
        addTripDialog.setTargetFragment(this, 1)
        addTripDialog.show(fragmentManager!!, "Add trip dialog")
    }

    override fun transfer(masterTrip: MasterTrip) {
        masterTripViewModel.insert(masterTrip)
    }
}
