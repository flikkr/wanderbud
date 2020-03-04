//package com.kazymir.tripweaver.unused
//
//import android.app.Dialog
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.widget.DatePicker
//import android.widget.EditText
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatDialogFragment
//import com.kazymir.tripweaver.R
//import com.kazymir.tripweaver.`object`.MasterTrip
//
//
//class AddTripDialog : AppCompatDialogFragment() {
//    private lateinit var editTextTripName: EditText
//    private lateinit var editTextStartDate: DatePicker
//    private var editTextEndDate: DatePicker? = null
//    private var listener: AddTripDialogListener? = null
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            // Get the layout inflater
//            val inflater = requireActivity().layoutInflater
//            val view = inflater.inflate(R.layout.dialog_add_trip, null)
//
//            // Inflate and set the layout for the dialog
//            // Pass null as the parent view because its going in the dialog layout
//            builder.setView(view)
//                // Add action buttons
//                .setPositiveButton(
//                    R.string.save
//                ) { dialog, id ->
//                    val tripName = editTextTripName.text.toString()
//                    val startDate = editTextStartDate.toString()
//                    val endDate = editTextEndDate?.toString()
//                    listener?.transfer(MasterTrip(tripName, startDate, endDate))
//                }
//                .setNegativeButton(
//                    R.string.cancel
//                ) { dialog, id ->
//                    getDialog()?.cancel()
//                }
//
//            editTextTripName = view.findViewById(R.id.titleTrip)
//            editTextStartDate = view.findViewById(R.id.startDate)
//            editTextEndDate = view.findViewById(R.id.endDate)
//
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        try {
//            listener = targetFragment as AddTripDialogListener
//        } catch (e: ClassCastException) {
//            // TODO catch exception
//            Log.e("AddTripDialog", "onAttach: ClassCastException: ${e.message}")
//        }
//
//    }
//
//    interface AddTripDialogListener {
//        fun transfer(masterTrip: MasterTrip)
//    }
//}
