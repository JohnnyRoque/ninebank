package com.example.newninebank

import android.app.Dialog
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.newninebank.databinding.DialogDesignBinding
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogFragmentNineBank(
    private val isANumber: Boolean,
    private val container: ViewGroup?,
) : DialogFragment() {
    companion object {
        const val DIALOGFRAGMENT = "DialogFragment"
    }
    private var _binding: DialogDesignBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_design, container, false)
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
                if (isANumber) {
                    binding.dialogEditText.inputType = (0x00002002)
                }else{
                    binding.textFieldLayout.visibility =VISIBLE
                }
                setView(binding.root)
            }
            builder.create()
        }
    }
}