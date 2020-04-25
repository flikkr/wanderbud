package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.MasterTrip
import com.kazymir.tripweaver.`object`.model.MasterTripViewModel
import kotlinx.android.synthetic.main.fragment_add_master_trip.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * This fragment is used to create new Master Trips
 */
class AddMasterTripFragment : Fragment(), View.OnClickListener {
    private lateinit var editTextTripTitle: EditText
    private lateinit var editTextStartDate: EditText
    private lateinit var editTextEndDate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_master_trip, container, false)

        editTextTripTitle = view.findViewById(R.id.title_trip)
        editTextStartDate = view.findViewById(R.id.start_date)
        editTextEndDate = view.findViewById(R.id.end_date)

        editTextStartDate.setOnClickListener(this)
        editTextEndDate.setOnClickListener(this)
        view.save_trip_button.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            // Handles the date picker widget for either start or end date
            R.id.start_date, R.id.end_date -> {
                val builder = MaterialDatePicker.Builder.dateRangePicker()
                val picker = builder.build()
                picker.show(fragmentManager!!, "Material DatePicker")

                // Formats time in milliseconds to gregorian date
                picker.addOnPositiveButtonClickListener {
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    var cal = Calendar.getInstance()

                    cal.timeInMillis = it.first!!
                    val start = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))

                    editTextStartDate.setText("${sdf.format(start.time)}")

                    cal.timeInMillis = it.second!!
                    val end = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))

                    editTextEndDate.setText("${sdf.format(end.time)}")
                }
            }
            // If user chooses to save the trip
            R.id.save_trip_button -> {
                val title = editTextTripTitle.text.toString()
                val startDate = editTextStartDate.text.toString()
                val endDate = editTextEndDate.text.toString()

                // Validate inputs
                val isValid = validateData(title, startDate, endDate)
                if (!isValid) return

                val mTrip = MasterTrip(title, startDate, endDate)

                // Add trip and return to previous screen
                val masterTripViewModel = ViewModelProvider(this).get(MasterTripViewModel::class.java)
                masterTripViewModel.insert(mTrip)
                v.findNavController().popBackStack()
            }
        }
    }

    // Function to validate the form
    fun validateData(title: String, startDate: String, endDate: String): Boolean {
        var isValid = true
        if (title.isEmpty() || title.length > 70) {
            editTextTripTitle.error = "Please enter a trip title (70 char. limit)"
            isValid = false
        }
        if (startDate.isEmpty() || endDate.isEmpty()) {
            editTextStartDate.error = ""
            editTextEndDate.error = ""
            isValid = false
        }
        return isValid
    }
}
