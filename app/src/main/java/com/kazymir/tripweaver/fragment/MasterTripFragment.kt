package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.adapter.TripAdapter
import com.kazymir.tripweaver.`object`.model.TripViewModel
import com.kazymir.tripweaver.activity.MainActivity

/**
 * This fragment is used to view all trips
 */
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

        val fab: FloatingActionButton = view.findViewById(R.id.floating_add_trip)
        fab.setOnClickListener(this)

        val args = MasterTripFragmentArgs.fromBundle(arguments!!)
        mTripId = args.masterTripId
        val mTripTitle = args.masterTripTitle

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = TripAdapter(context!!)
        recyclerView.adapter = adapter

        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)

        // Set toolbar title
        var toolbar = (activity as MainActivity).supportActionBar
        toolbar?.title = mTripTitle

        // Handle swipe actions on trip
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Delete trip on swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                adapter.removeItem(viewHolder, tripViewModel)
            }
        }

        // Binding
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Initialise trip to recyclerview
        tripViewModel.getTripsByMasterTripId(mTripId!!)?.let { livedata ->
            livedata.observe(viewLifecycleOwner, Observer { trips ->
                // Update the cached copy of the trips in the adapter.
                trips?.let { adapter.setTrips(it) }
            })
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            // Handles click when adding a trip to a master trip
            R.id.floating_add_trip -> {
                val action = MasterTripFragmentDirections.actionMasterTripFragmentToAddTripFragment2(mTripId!!)
                v.findNavController().navigate(action)
            }
        }
    }

}
