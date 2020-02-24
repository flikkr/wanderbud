package com.kazymir.tripweaver.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.kazymir.tripweaver.R


class AddTripDialog : AppCompatDialogFragment() {
    private var editTextTripName: EditText? = null
    private var listener: AddTripDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_trip, null)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                .setPositiveButton(
                    R.string.save
                ) { dialog, id ->
                    val tripName = editTextTripName?.text.toString()
                    Toast.makeText(context, tripName, Toast.LENGTH_LONG).show()
                    listener?.applyTexts(tripName)
                }
                .setNegativeButton(
                    R.string.cancel
                ) { dialog, id ->
                    Toast.makeText(context, "AAAAAH", Toast.LENGTH_LONG).show()
                    getDialog()?.cancel()
                }
            editTextTripName = view.findViewById(R.id.titleTrip)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = targetFragment as AddTripDialogListener
        } catch (e: ClassCastException) {
            // TODO catch exception
            Log.e("AddTripDialog", "onAttach: ClassCastException: ${e.message}")
        }

    }

    interface AddTripDialogListener {
        fun applyTexts(tripName: String)
    }
}
