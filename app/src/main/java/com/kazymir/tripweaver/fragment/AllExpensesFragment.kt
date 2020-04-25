package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.adapter.ExpenseAdapter
import com.kazymir.tripweaver.`object`.model.ExpenseViewModel
import com.kazymir.tripweaver.`object`.model.TripViewModel
import kotlinx.android.synthetic.main.fragment_all_expenses.view.*

/**
 * This fragment is used to view all expenses for a trip
 */
class AllExpensesFragment : Fragment(), View.OnClickListener {
    private lateinit var expenseViewModel: ExpenseViewModel
    private var tripId: Long = 0

    private lateinit var expenseTitle: EditText
    private lateinit var expenseTypeSpinner: Spinner
    private lateinit var expenseAmount: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_expenses, container, false)
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        val args = AllExpensesFragmentArgs.fromBundle(arguments!!)
        tripId = args.tripId

        val fab = view.floating_add_expense
        fab.setOnClickListener(this)

        val recyclerView: RecyclerView = view.expenses_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = ExpenseAdapter(context!!)
        recyclerView.adapter = adapter

        // Handle swipe actions
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Remove item on swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                adapter.removeItem(viewHolder, expenseViewModel)
            }
        }

        // Binding
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Display expenses in recyclerview
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.getLiveDataExpensesByTripId(tripId)
            .observe(viewLifecycleOwner, Observer { expenses ->
                expenses?.let { adapter.setTrips(it) }
            })

        return view
    }

    // Open dialog to create new expense
    private fun openDialogExpense() {
        val builder = AlertDialog.Builder(context!!)
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_add_expense, null)
        builder.setView(view)

        val expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        with(builder) {
            setTitle("Add expense")
            expenseTitle = view?.findViewById(R.id.expense_title)!!
            expenseTypeSpinner = view?.findViewById(R.id.expense_type_spinner)
            expenseAmount = view?.findViewById(R.id.expense_amount)

            var adapter = ArrayAdapter(
                context!!,
                android.R.layout.simple_list_item_1,
                Expense.expenseType.keys.toList()
            )
            expenseTypeSpinner.adapter = adapter

            // On save
            setPositiveButton(android.R.string.yes) { dialog, which ->
                val title = expenseTitle.text.toString()
                val expType = expenseTypeSpinner.selectedItem.toString()
                val amount = expenseAmount.text.toString()

                // Validate inputs
                val isValid = validateData(title, amount)
                if (isValid) {
                    val expense = Expense(tripId, "£", title, amount.toFloat(), expType)
                    expenseViewModel.insert(expense)
                }
            }
            // On cancel
            setNegativeButton(android.R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
        }

        builder.show()
    }

    // Function to validate the form
    private fun validateData(title: String, amount: String?): Boolean {
        var isValid = true
        if (title.isEmpty() || title.length > 70) {
            expenseTitle.error = "Please enter an expense name (70 char. limit)"
            isValid = false
        }
        if (amount == null || amount == "") {
            expenseAmount.error = "Please enter a valid amount."
            isValid = false
        }
        return isValid
    }

    override fun onClick(v: View) {
        when (v.id) {
            // Open add expense dialog
            R.id.floating_add_expense -> openDialogExpense()
        }
    }
}
