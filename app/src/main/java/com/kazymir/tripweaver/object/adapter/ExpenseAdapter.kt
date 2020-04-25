package com.kazymir.tripweaver.`object`.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kazymir.tripweaver.R
import com.kazymir.tripweaver.`object`.Expense
import com.kazymir.tripweaver.`object`.model.ExpenseViewModel
import com.kazymir.tripweaver.`object`.model.TripViewModel
import kotlinx.android.synthetic.main.item_expense.view.*

/**
 * Recycler view adapter for expenses
 */
class ExpenseAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder>() {

    private var expenses: MutableList<Expense> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseHolder(
            itemView
        )
    }

    // Binds viewholder attributes
    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {
        val currentExpense = expenses[position]
        holder.expenseId = currentExpense.eid
        holder.title.text = currentExpense.title
        holder.amount.text = currentExpense.amount.toString() + currentExpense.currency
        holder.category.text = currentExpense.type
        holder.date.text = currentExpense.created
    }

    // Returns the number of items in list
    override fun getItemCount(): Int = expenses.size

    // Sets list items
    fun setTrips(expense: List<Expense>) {
        this.expenses = expense as MutableList<Expense>
        expenses.sortedWith(compareBy { it.created })
        notifyDataSetChanged()
    }

    // Remove list item
    fun removeItem(viewHolder: RecyclerView.ViewHolder, viewModel: ExpenseViewModel) {
        viewModel.delete(expenses[viewHolder.adapterPosition])
        expenses.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    class ExpenseHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var view: View = itemView
        var expenseId: Long = 0
        val title: TextView = itemView.expense_title
        val amount: TextView = itemView.expense_amount
        val category: TextView = itemView.expense_category
        val date: TextView = itemView.expense_date

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(itemView: View) {
            // Could add another screen here?
        }
    }
}