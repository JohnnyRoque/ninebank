package com.example.newninebank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.ModalBottomSheetContentBinding
import com.example.newninebank.model.DataSet
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
    private val sharedViewModel: NineBankViewModel by activityViewModels()
    private var _binding: ModalBottomSheetContentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.modal_bottom_sheet_content, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val submitTypes = TypesOfAccountAdapter(requireContext(),this)
        submitTypes.typesAsyncDiff.submitList(DataSet().loadTypesOfAccount())
        submitTypes.notifyItemInserted(submitTypes.typesAsyncDiff.currentList.size)

        binding.apply {
            recyclerAdapter = submitTypes
            lifecycleOwner = viewLifecycleOwner
        }
        binding.buttonConfirm.setOnClickListener {
            dismiss()

        }
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        const val MODAL_TAG = "ModalBottomSheet"
    }

    fun confirmTypeOfAccount(account:String){
        sharedViewModel.getUserInput(account)
    }
}