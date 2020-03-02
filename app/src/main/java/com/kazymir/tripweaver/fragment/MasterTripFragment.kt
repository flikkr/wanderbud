package com.kazymir.tripweaver.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.model.TripViewModel
import kotlinx.android.synthetic.main.fragment_master_trip.view.*

class MasterTripFragment : Fragment() {
    private lateinit var tripViewModel: TripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = MasterTripFragmentArgs.fromBundle(arguments!!)
        view.test.text = args.masterTripId.toString()
    }

}
