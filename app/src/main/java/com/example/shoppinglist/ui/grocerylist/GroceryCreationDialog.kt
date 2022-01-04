package com.example.shoppinglist.ui.grocerylist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.GroceryCreationDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroceryCreationDialog(private val id: Long) : DialogFragment() {

    private var _binding: GroceryCreationDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GroceryDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //creating a dialog
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = GroceryCreationDialogBinding.inflate(inflater)
        val dialog = builder.setView(binding.root).create()

        setOnClickListeners(dialog)
        observeViewModel(dialog)

        return dialog
    }


    private fun observeViewModel(dialog: AlertDialog) {
        val lifecycleOwner = this.requireParentFragment().viewLifecycleOwner
        viewModel.errorMessage.observe(lifecycleOwner, {
            if (it != null){
                binding.groceryQuantityInputLayout.error = it
            }
        })
        viewModel.dismissDialog.observe(lifecycleOwner, {
            if (it){
                dialog.dismiss()
            }
        })
    }

    private fun setOnClickListeners(dialog: AlertDialog) {
        binding.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        binding.saveButton.setOnClickListener {
            viewModel.addGrocery(
                binding.groceryName.editText?.text.toString(),
                binding.groceryQuantityInputLayout.editText?.text.toString(),
                id
            )
        }
    }

}