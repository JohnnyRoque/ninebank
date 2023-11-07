package com.example.newninebank.ui.enter_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.R
import com.example.newninebank.databinding.ModalBottomSheetEnterAccountDesignBinding
import com.example.newninebank.model.NineBankViewModel
import com.example.newninebank.util.Routes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(val parent: Fragment) : BottomSheetDialogFragment() {
  private  var _binding : ModalBottomSheetEnterAccountDesignBinding? = null
    val binding get() = _binding!!
    private val sharedViewModel : NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.modal_bottom_sheet_enter_account_design,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
        }

        binding.signInButton.setOnClickListener {
            sharedViewModel.onClickNavigate(parent,Routes.HOME_FRAGMENT)
            this.dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}