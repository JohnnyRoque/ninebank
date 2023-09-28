package com.example.newninebank

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.newninebank.databinding.DialogDesignBinding
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs

class DialogFragmentNineBank(val value: String, private val isANumber:Boolean, private val container:ViewGroup?,val lifecycleOwnerD: LifecycleOwner) : DialogFragment() {
    private var _binding: DialogDesignBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DataBindingUtil.inflate(layoutInflater,R.layout.dialog_design,container,false)

        sharedViewModel.accountCurrency.observe(lifecycleOwnerD){
            binding.availableBalanceTextviewDialog.text = getString(R.string.available_balance_dialog,it)
        }

        return activity.let {
            val builder = MaterialAlertDialogBuilder(requireContext())
            builder.apply {
                setNegativeButton("Cancelar") { dialog, which -> }
                setPositiveButton(getText(R.string.continue_text)) { dialog, which ->
                    if (!binding.dialogEditText.text.isNullOrBlank()) {
                        sharedViewModel.calSpent(binding.dialogEditText.text.toString().toDouble())
                        dismiss()
                    }
                    if (isANumber){
                        binding.dialogEditText.inputType =0x00002002
                    }
                }
                setView(binding.root)
            }
            builder.create()
        }
    }
}