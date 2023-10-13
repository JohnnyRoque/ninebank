package com.example.newninebank.ui

import android.app.Dialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.R
import com.example.newninebank.databinding.DialogDesignBinding
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogFragmentNineBank(
) : DialogFragment() {
    companion object {
        const val DIALOG_FRAGMENT = "DialogFragment"
    }
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         val binding: DialogDesignBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_design, null, false)
        binding.apply {
            viewModel = sharedViewModel
        }

        return activity.let {
            val builder = MaterialAlertDialogBuilder(requireContext())
            builder.apply {
                setNegativeButton(getText(R.string.cancel_text)) { _, _ -> }
                setPositiveButton(getText(R.string.continue_text)) { _, _ ->
                    if (!binding.dialogEditText.text.isNullOrBlank()) {
                        sharedViewModel.calSpent(binding.dialogEditText.text.toString().toDouble())
                        dismiss()
                    }
                }
                setView(binding.root)
            }
            builder.create()
        }
    }
}