package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.TripWithExpenses
import com.kazymir.tripweaver.model.TripViewModel

class TripFragment : Fragment() {
    private lateinit var tripViewModel: TripViewModel
    private var tripId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trip, container, false)

        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)

        val args = TripFragmentArgs.fromBundle(arguments!!)
        val tripId = args.tripId

//        budget(view)

        return view
    }

    fun budget(v: View) {
        val totalBudgetTextView: TextView = v.findViewById(R.id.total_budget)
//        totalBudgetTextView.text = tripViewModel.getTripWithExpenses(tripId!!).value?.trip?.tBudget.toString()
        Log.d("Expenses", tripViewModel.getTripWithExpenses(tripId!!).value.toString())
    }

}
