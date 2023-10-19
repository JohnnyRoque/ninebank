package com.example.newninebank.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newninebank.NineBankRecyclerView
import com.example.newninebank.R
import com.example.newninebank.TextRecyclerView
import com.example.newninebank.databinding.FragmentHomeBinding
import com.example.newninebank.model.NineBankViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToEnterAccountFragment()
            )
        }
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            buttonRecyclerAdapter = NineBankRecyclerView(requireParentFragment(),requireContext())
            textRecyclerAdapter = TextRecyclerView(requireContext())
            homeFragment = this@HomeFragment
            navToFragmentName = "FinancialStatement"
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
