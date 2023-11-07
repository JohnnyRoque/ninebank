package com.example.newninebank.ui.enter_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.R
import com.example.newninebank.databinding.FragmentEnterAccountBinding
import com.example.newninebank.model.NineBankViewModel
import com.example.newninebank.util.Routes

class EnterAccountFragment : Fragment() {
    private var _binding: FragmentEnterAccountBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_enter_account, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            viewModel = sharedViewModel
            enterFragment = this@EnterAccountFragment
            navToHomeFragment = Routes.HOME_FRAGMENT
            navToOpenAccountFragment = Routes.OPEN_ACCOUNT
        }
        binding.buttonEnterAccount.setOnClickListener {
            createNewBottomSheet()

        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun createNewBottomSheet() {
        ModalBottomSheet(this@EnterAccountFragment).show(parentFragmentManager,"ModalBottomSheet")

    }
}