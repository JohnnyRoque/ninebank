package com.example.newninebank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.DialogDesignBinding
import com.example.newninebank.model.NineBankViewModel

class DialogFragmentNineBank(val value: String,val isANumber:Boolean) : DialogFragment() {
    private var _binding: DialogDesignBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.dialog_design, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
        }

        val transferValue = binding.dialogEditText
        if (isANumber){
            transferValue.inputType =0x00002002

        }

        binding.dialogButtonContinue.setOnClickListener {
            if (!transferValue.text.isNullOrBlank()) {
                sharedViewModel.calSpent(transferValue.text.toString().toDouble())
                dismiss()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}