package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.adapter.TripAdapter
import com.kazymir.tripweaver.model.TripViewModel

class MasterTripFragment : Fragment(), View.OnClickListener {
    private lateinit var tripViewModel: TripViewModel
    private var mTripId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingAddTrip)
        fab.setOnClickListener(this)

        val args = MasterTripFragmentArgs.fromBundle(arguments!!)
        mTripId = args.masterTripId

        activity?.actionBar?.title = "HEASDNASKDBNA"

        val test = view.findViewById<TextView>(R.id.tester)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter =
            TripAdapter(context!!)
        recyclerView.adapter = adapter

        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
        tripViewModel.getTripsByMasterTripId(mTripId!!)?.let { livedata ->
            livedata.observe(viewLifecycleOwner, Observer { trips ->
                // Update the cached copy of the trips in the adapter.
                trips?.let { adapter.setTrips(it) }
            })
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.floatingAddTrip -> {
                val action = MasterTripFragmentDirections.actionMasterTripFragmentToAddTripFragment2(mTripId!!)
                v.findNavController().navigate(action)
            }
        }
    }

}