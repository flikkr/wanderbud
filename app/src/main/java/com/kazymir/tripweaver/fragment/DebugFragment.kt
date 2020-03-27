package com.kazymir.tripweaver.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.model.MasterTripViewModel
import kotlinx.android.synthetic.main.fragment_debug.view.*

class DebugFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_debug, container, false)
        val clearTripsBtn = view.clear_trip
        clearTripsBtn.setOnClickListener(this)

        return view

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.clear_trip -> {
                val masterTripViewModel = ViewModelProvider(this).get(MasterTripViewModel::class.java)
                masterTripViewModel.clear()
                Toast.makeText(context, "Cleared MasterTrip table", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
