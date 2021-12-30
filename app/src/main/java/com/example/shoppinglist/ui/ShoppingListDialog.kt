package com.example.shoppinglist.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.shoppinglist.databinding.ListCreationDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListDialog : DialogFragment() {

    private var _binding: ListCreationDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListsViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        _binding = ListCreationDialogBinding.inflate(inflater)

        val dialog = builder.setView(binding.root).create()
        binding.cancelButton.setOnClickListener {
            DialogInterface.OnCancelListener { dialog.cancel() }
        }
        binding.saveButton.setOnClickListener {

        }

        return dialog
    }
}