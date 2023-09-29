package com.example.newninebank


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.newninebank.DialogFragmentNineBank.Companion.DIALOGFRAGMENT
import com.example.newninebank.databinding.FragmentHomeBinding
import com.example.newninebank.model.NineBankViewModel



class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NineBankViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            buttonRecyclerAdapter = NineBankRecyclerView(requireContext())
            textRecyclerAdapter = TextRecyclerView(requireContext(), dataSetOpenAccount = null)
            homeFragment = this@HomeFragment
            navToFragmentName = "FinancialStatement"
        }

        super.onViewCreated(view, savedInstanceState)
    }
    fun createMaterialDialog() {
        DialogFragmentNineBank(true,null).show(parentFragmentManager, DIALOGFRAGMENT)
    }
}
