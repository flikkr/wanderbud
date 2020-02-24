package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Database
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Trip


class TripFragment : Fragment(), View.OnClickListener, AddTripDialog.AddTripDialogListener {
    private var textViewTripName: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_trip, container, false)

        textViewTripName = view.findViewById(R.id.tripName)
        val addTripBtn = view.findViewById<Button>(R.id.addTripBtn)
        addTripBtn.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addTripBtn -> openDialog()
        }
    }

    private fun openDialog() {
        val addTripDialog = AddTripDialog()
        addTripDialog.setTargetFragment(this, 1)
        addTripDialog.show(fragmentManager!!, "Add trip dialog")
    }

    override fun applyTexts(tripName: String) {
        textViewTripName?.text = tripName
        val trip = Trip(tripName, "0", "0", "test")
    }
}