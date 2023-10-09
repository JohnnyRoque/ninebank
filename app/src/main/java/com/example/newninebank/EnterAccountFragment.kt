package com.example.newninebank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.FragmentEnterAccountBinding
import com.example.newninebank.model.NineBankViewModel

class EnterAccountFragment : Fragment() {
    private var _binding: FragmentEnterAccountBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_enter_account, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            enterFragment = this@EnterAccountFragment
            navToHomeFragment = resources.getTextArray(R.array.listOfFragments)[1].toString()
            navToOpenAccountFragment = resources.getTextArray(R.array.listOfFragments)[2].toString()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}