//package com.kazymir.tripweaver.util
//
//import android.app.Activity
//import android.app.AlertDialog
//import android.content.Context
//import android.widget.ArrayAdapter
//import android.widget.EditText
//import android.widget.Spinner
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelStoreOwner
//import com.kazymir.tripweaver.R
//import com.kazymir.tripweaver.`object`.Expense
//import com.kazymir.tripweaver.`object`.model.ExpenseViewModel
//
///**
// *  Class used to access alert dialogs that are re-used in multiple fragments
// */
//class DialogBuilder() {
//    companion object {
//        private fun openDialogExpense(context: Context, activity: Activity, viewModelStoreOwner: ViewModelStoreOwner, tripId: Long) {
//            val builder = AlertDialog.Builder(context!!)
//            val view = activity.layoutInflater?.inflate(R.layout.dialog_add_expense, null)
//            builder.setView(view)
//
//            val expenseViewModel = ViewModelProvider(viewModelStoreOwner).get(ExpenseViewModel::class.java)
//
//            with(builder) {
//                setTitle("Add expense")
//                val expenseTitle: EditText = view?.findViewById(R.id.expense_title)!!
//                val expenseTypeSpinner: Spinner = view?.findViewById(R.id.expense_type_spinner)
//                val expenseAmount: EditText = view?.findViewById(R.id.expense_amount)
//
//                var adapter = ArrayAdapter(
//                    context!!,
//                    android.R.layout.simple_list_item_1,
//                    Expense.expenseType.keys.toList()
//                )
//                expenseTypeSpinner.adapter = adapter
//
//                var result: Pair<Float, String>? = null
//                setPositiveButton(android.R.string.yes) { _, _ ->
//                    val title = expenseTitle.text.toString()
//                    val expType = expenseTypeSpinner.selectedItem.toString()
//                    val amount = expenseAmount.text.toString().toFloat()
//
//                    val expense = Expense(tripId, "£", title, amount, expType)
//                    expenseViewModel.insert(expense)
//                    result = Pair(amount, expType)
//
//                    // TODO return result from dialog
////                    return result
//                }
//                setNegativeButton(android.R.string.no) { dialog, _ ->
//                    dialog.dismiss()
//                }
//            }
//
//            builder.show()
//        }
//    }
//}
