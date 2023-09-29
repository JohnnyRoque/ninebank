package com.example.newninebank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.FragmentOpenAccountBinding
import com.example.newninebank.model.DataSet
import com.example.newninebank.model.NineBankViewModel

class OpenAccountFragment : Fragment() {
    var _binding : FragmentOpenAccountBinding? = null
    val binding get() = _binding!!
    val sharedViewModel : NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_open_account,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
        }
        binding.openAccountRecycler.adapter = TextRecyclerView(requireContext(), dataSetOpenAccount = DataSet().loadTextsOpenAccount())
        super.onViewCreated(view, savedInstanceState)
    }
}