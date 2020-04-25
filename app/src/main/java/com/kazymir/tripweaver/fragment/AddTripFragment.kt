package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Location
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.model.TripViewModel
import com.kazymir.tripweaver.util.AssetManager.Companion.getJsonDataFromAsset
import kotlinx.android.synthetic.main.fragment_add_master_trip.view.*


/**
 * This fragment is used to create new trips
 */
class AddTripFragment : Fragment(), View.OnClickListener {
    private lateinit var editTextTripTitle: EditText
    private lateinit var editTextBudget: EditText
    private lateinit var spinnerCountry: Spinner
    private lateinit var locations: Map<String, String>
    private var mTripId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_trip, container, false)

        // Collect arguments (ID)
        val args = AddTripFragmentArgs.fromBundle(arguments!!)
        mTripId = args.masterTripId

        editTextTripTitle = view.findViewById(R.id.title_trip)
        editTextBudget = view.findViewById(R.id.budget)
        spinnerCountry = view.findViewById(R.id.country)
        loadCountries()

        view.save_trip_button.setOnClickListener(this)

        locations.forEach { (s, s2) -> Log.d("Map", "country: $s, code: $s2") }
        var adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, locations.keys.toList())
        spinnerCountry.adapter = adapter

        return view
    }

    // Retrieves country list stored as JSON in assets/countries.json
    private fun loadCountries() {
        val countriesJSON = getJsonDataFromAsset(context!!, "countries.json")

        // Use GSON to interpret JSON data
        val tokenizer = object : TypeToken<List<Location>>() {}.type
        val listLocations: List<Location> = Gson().fromJson(countriesJSON, tokenizer)
        // Create map of country => code
        locations = listLocations.associateBy({ it.country }, { it.countryCode })
    }

    override fun onClick(v: View) {
        when (v.id) {
            // Save trip
            R.id.save_trip_button -> {
                val title = editTextTripTitle.text.toString()
                val budget = editTextBudget.text.toString()
                val country = spinnerCountry.selectedItem.toString()
                val countryCode = locations[country]

                // Validate inputs
                val isValid = validateData(title, budget)
                if (!isValid) return

                // Should normally be used for caching, not useful in this prototype so omitted
//                val location = Location(country, countryCode!!)

                // Create new trip, add to database and return to previous screen
                val trip = Trip(mTripId!!, title, country)
                trip.tBudget = budget.toFloat()

                val tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
                tripViewModel.insert(trip)
                v.findNavController().popBackStack()
            }
        }
    }

    // Function to validate the form
    fun validateData(title: String, budget: String): Boolean {
        var isValid = true
        if (title.isEmpty() || title.length > 70) {
            editTextTripTitle.error = "Please enter a trip title (70 char. limit)"
            isValid = false
        }
        if (budget.isEmpty()) {
            editTextBudget.error = "Please enter your trip budget."
            isValid = false
        }
        return isValid
    }
}
