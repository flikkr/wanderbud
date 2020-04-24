package com.kazymir.tripweaver.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.Trip
import com.kazymir.tripweaver.`object`.model.ExpenseViewModel
import com.kazymir.tripweaver.`object`.model.TripViewModel
import kotlinx.android.synthetic.main.fragment_trip.*


class TripFragment : Fragment(), View.OnClickListener {
    private lateinit var tripViewModel: TripViewModel
    private lateinit var chart: PieChart
    private var tripId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trip, container, false)
        val args = TripFragmentArgs.fromBundle(arguments!!)
        tripId = args.tripId

        chart = view.findViewById(R.id.pieChart1)
        tripViewModel = ViewModelProvider(this).get(TripViewModel::class.java)
        tripViewModel.getLiveDataTrip(tripId).observe(viewLifecycleOwner, Observer { trip ->
            budget(view, trip)
            if (trip.cBudget == 0f) {
                chart.visibility = View.GONE
                no_data_text.visibility = View.VISIBLE
            } else {
                chart.visibility = View.VISIBLE
                no_data_text.visibility = View.GONE
            }
        })

        // Chart settings
        with(chart) {
            data = generatePieData()
            description.isEnabled = false
            holeRadius = 45f
            transparentCircleRadius = 50f
            legend.isWordWrapEnabled = true
            setNoDataText("No chart data available.");
        }

        val addExpenseBtn: Button = view.findViewById(R.id.add_expense_btn)
        addExpenseBtn.setOnClickListener(this)
        val viewExpensesBtn: Button = view.findViewById(R.id.view_expense_btn)
        viewExpensesBtn.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.add_expense_btn -> openDialogExpense()
            R.id.view_expense_btn -> {
                val action = TripFragmentDirections.actionTripFragmentToAllExpensesFragment(tripId!!)
                v.findNavController().navigate(action)
            }
        }
    }

    // Initialise budget of trip
    private fun budget(v: View, trip: Trip) {
        val totalBudgetTextView: TextView = v.findViewById(R.id.total_budget)
        val currentBudgetTextView: TextView = v.findViewById(R.id.current_budget)
        totalBudgetTextView.text = trip.tBudget.toString()
        currentBudgetTextView.text = trip.cBudget.toString()

        if (trip.cBudget > trip.tBudget) currentBudgetTextView.setTextColor(
            resources.getColor(R.color.colorNegative, null)
        ) else currentBudgetTextView.setTextColor(resources.getColor(R.color.colorPositive, null))
    }

    private fun generatePieData(): PieData? {
        val expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        val expenses = expenseViewModel.getExpensesByTripId(tripId)
        val expensesMap = listToMap(expenses)
        val entries: ArrayList<PieEntry> = ArrayList()

        for (i in expensesMap) {
            entries.add(PieEntry(i.value, i.key))
        }

        // Dataset settings
        val ds = PieDataSet(entries, "Expenses by type")
        with(ds) {
            setColors(*ColorTemplate.COLORFUL_COLORS)
            sliceSpace = 2f
            valueTextColor = Color.WHITE
            valueTextSize = 12f
        }
        return PieData(ds)
    }

    private fun updatePieData(amount: Float, expType: String) {
        val trip = tripViewModel.getTrip(tripId)
        trip.cBudget += amount
        tripViewModel.update(trip)

        chart.data.dataSet.addEntry(PieEntry(amount, expType))
        chart.notifyDataSetChanged()
    }

    // Take in a list of expenses and transforms it to a map using lambdas
    private fun listToMap(expenses: List<Expense>): MutableMap<String, Float> {
        val expensesMap: MutableMap<String, Float> = mutableMapOf()

        // convert to map
        expenses.forEach {
            if (expensesMap.containsKey(it.type)) {
                val count = expensesMap[it.type]!!
                expensesMap[it.type] = (count + it.amount)
            } else expensesMap[it.type] = it.amount
        }

        return expensesMap
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
                updatePieData(amount, expType)
            }
            setNegativeButton(android.R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
        }

        builder.show()
    }
}
