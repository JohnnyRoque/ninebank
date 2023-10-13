package com.example.newninebank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.newninebank.databinding.ModalBottomSheetContentBinding
import com.example.newninebank.model.DataSet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
    private  val  submitTypes = TypesOfAccountAdapter()
    init {
        submitTypes.typesAsyncDiff.submitList(DataSet().loadTypesOfAccount())
        submitTypes.notifyItemInserted(submitTypes.typesAsyncDiff.currentList.size)
    }

     private var _binding: ModalBottomSheetContentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.modal_bottom_sheet_content,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            recyclerAdapter = submitTypes
            lifecycleOwner = viewLifecycleOwner
        }


        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        const val MODAL_TAG = "ModalBottomSheet"
    }
}