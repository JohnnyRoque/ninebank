package com.example.newninebank.ui.home_Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newninebank.R
import com.example.newninebank.databinding.FragmentHomeBinding
import com.example.newninebank.model.DataSet
import com.example.newninebank.model.NineBankViewModel
import com.example.newninebank.util.Routes


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
        val recyclerButtonAdapter = NineBankRecyclerView(requireContext()){
            sharedViewModel.onClickNavigate(this@HomeFragment,it)
        }
        val textRecyclerViewAdapter= TextRecyclerView()
        textRecyclerViewAdapter.asyncDiff.submitList(DataSet().loadTexts())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            buttonRecyclerAdapter = recyclerButtonAdapter
            textRecyclerAdapter = textRecyclerViewAdapter
            homeFragment = this@HomeFragment
            navToFragmentName = Routes.FINANCIAL_STATEMENT
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
