package com.example.newninebank.ui.financial_statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newninebank.R
import com.example.newninebank.databinding.FragmentFinancialStatementBinding
import com.example.newninebank.model.NineBankViewModel




class FinancialStatementFragment : Fragment() {
    private var _binding: FragmentFinancialStatementBinding? = null
    val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_financial_statement,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            transactionHistoryRecycler = TransactionHistoryRecycler(sharedViewModel.transformList())
            viewModel = sharedViewModel
        }
        binding.appbarFs.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}