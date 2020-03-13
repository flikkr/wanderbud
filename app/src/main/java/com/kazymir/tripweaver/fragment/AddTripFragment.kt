package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Location
import com.kazymir.tripweaver.util.AssetManager.Companion.getJsonDataFromAsset
import kotlinx.android.synthetic.main.fragment_add_master_trip.view.*


class AddTripFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_trip, container, false)

        val editTextTripTitle: EditText = view.findViewById(R.id.title_trip)
        val editTextCountry: AutoCompleteTextView = view.findViewById(R.id.country)

        view.save_trip_button.setOnClickListener(this)
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_expandable_list_item_1, loadCountries()
        )
        editTextCountry.setAdapter(adapter)

        return view
    }

    fun loadCountries(): List<String> {
        val countriesJSON = getJsonDataFromAsset(context!!, "countries.json")
        Log.i("data", countriesJSON)

        val listLocations = object : TypeToken<List<Location>>() {}.type
        var locations: List<Location> = Gson().fromJson(countriesJSON, listLocations)
        var countries: MutableList<String> = mutableListOf()
        
        locations.forEachIndexed { i, location ->
            countries.add(i, location.country)
            Log.i("date", countries[i])
        }

        return countries
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.save_trip_button -> {
//                TODO("Need to validate user inputs")
//
//                val title = editTextTripTitle.text.toString()
//
//                val mTrip = MasterTrip(title)
//                val masterTripViewModel = ViewModelProvider(this).get(MasterTripViewModel::class.java)
//
//                masterTripViewModel.insert(mTrip)
//                v.findNavController().popBackStack()
//            }
        }
    }
}
