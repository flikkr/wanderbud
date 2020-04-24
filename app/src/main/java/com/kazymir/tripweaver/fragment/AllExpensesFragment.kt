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
import kotlinx.android.synthetic.main.fragment_all_expenses.view.*

class AllExpensesFragment : Fragment(), View.OnClickListener {
    private lateinit var expenseViewModel: ExpenseViewModel
    private var tripId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_expenses, container, false)
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        val args = TripFragmentArgs.fromBundle(arguments!!)
        tripId = args.tripId

        val fab = view.floating_add_expense
        fab.setOnClickListener(this)

        val recyclerView: RecyclerView = view.expenses_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = ExpenseAdapter(context!!)
        recyclerView.adapter = adapter

        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                adapter.removeItem(viewHolder, expenseViewModel)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        expenseViewModel.getLiveDataExpensesByTripId(tripId)
            .observe(viewLifecycleOwner, Observer { expenses ->
                expenses?.let { adapter.setTrips(it) }
            })

        return view
    }

    private fun openDialogExpense() {
        val builder = AlertDialog.Builder(context!!)
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_add_expense, null)
        builder.setView(view)

        val expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        with(builder) {
            setTitle("Add expense")
            val expenseTitle: EditText = view?.findViewById(R.id.expense_title)!!
            val expenseTypeSpinner: Spinner = view?.findViewById(R.id.expense_type_spinner)
            val expenseAmount: EditText = view?.findViewById(R.id.expense_amount)

            var adapter = ArrayAdapter(
                context!!,
                android.R.layout.simple_list_item_1,
                Expense.expenseType.keys.toList()
            )
            expenseTypeSpinner.adapter = adapter

            setPositiveButton(android.R.string.yes) { dialog, which ->
                val title = expenseTitle.text.toString()
                val expType = expenseTypeSpinner.selectedItem.toString()
                val amount = expenseAmount.text.toString().toFloat()

                val expense = Expense(tripId, "£", title, amount, expType)
                expenseViewModel.insert(expense)
            }
            setNegativeButton(android.R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
        }

        builder.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.floating_add_expense -> openDialogExpense()
        }
    }
}
