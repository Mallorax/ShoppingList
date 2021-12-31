package com.example.shoppinglist.ui.grocerylist

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.GroceryCreationDialogBinding
import com.example.shoppinglist.databinding.ListCreationDialogBinding
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.ui.shoppinglists.ShoppingListsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryCreationDialog: DialogFragment() {

    private var _binding: GroceryCreationDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GroceryListViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = GroceryCreationDialogBinding.inflate(inflater)
        val dialog = builder.setView(binding.root).create()

        binding.cancelButton.setOnClickListener {
            DialogInterface.OnCancelListener { dialog.cancel() }
        }
        binding.saveButton.setOnClickListener {
            if (viewModel.shoppingListId != null){
                val grocery = Grocery(
                    binding.groceryName.editText?.text.toString(),
                    binding.groceryQuantityInputLayout.editText?.text.toString().toInt(),
                    viewModel.shoppingListId!!
                )
                viewModel.saveGrocery(grocery)
            }
            dialog.dismiss()
        }
        return dialog
    }

}