package com.example.newninebank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.FragmentOpenAccountBinding
import com.example.newninebank.model.NineBankViewModel

class OpenAccountFragment : Fragment() {
    private var _binding : FragmentOpenAccountBinding? = null
    val binding get() = _binding!!
    lateinit var recyclerChat : TextRecyclerView
    private val sharedViewModel : NineBankViewModel by activityViewModels()

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
            lifecycleOwner = viewLifecycleOwner
        }
        setupRecycler()
        sharedViewModel.openAccountChatList.observe(viewLifecycleOwner){
            recyclerChat.asyncDiff.submitList(it)

        }

        binding.buttonSend.setOnClickListener {
            sharedViewModel.getUserName(binding.editTextChatInput.text.toString())
        }


        super.onViewCreated(view, savedInstanceState)
    }
fun setupRecycler(){
    recyclerChat = TextRecyclerView(requireContext())
    binding.openAccountRecycler.adapter = recyclerChat


}


}