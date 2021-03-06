package com.example.shoppinglist.ui.shoppinglists.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.ListCreationDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShoppingListDialog : DialogFragment() {

    private var _binding: ListCreationDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = ListCreationDialogBinding.inflate(inflater)

        val dialog = builder.setView(binding.root).create()
        binding.cancelButton.setOnClickListener {
            DialogInterface.OnCancelListener { dialog.cancel() }
        }
        binding.saveButton.setOnClickListener {
            viewModel.saveShoppingList(
                getSelectedDate(binding.datePicker),
                binding.shoppingListName.editText?.text.toString()
            )
            dialog.dismiss()
        }

        return dialog
    }

    private fun getSelectedDate(datePicker: DatePicker): Calendar{
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        return calendar
    }
}