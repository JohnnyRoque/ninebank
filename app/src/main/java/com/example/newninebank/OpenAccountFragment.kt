package com.example.newninebank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newninebank.databinding.FragmentOpenAccountBinding
import com.example.newninebank.model.NineBankViewModel

class OpenAccountFragment : Fragment() {

    private var _binding: FragmentOpenAccountBinding? = null
    val binding get() = _binding!!
    private lateinit var recyclerChat: TextRecyclerView
    private val sharedViewModel: NineBankViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_open_account, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sharedViewModel.loadTextsOpenAccount()
        recyclerChat = TextRecyclerView(requireContext())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            textRecyclerAdapter = recyclerChat
        }
        sharedViewModel.openAccountChatList.observe(viewLifecycleOwner) {
            recyclerChat.asyncDiff.submitList(it)
            recyclerChat.notifyItemInserted(it.size + 1)
            Log.d(TAG,recyclerChat.asyncDiff.currentList.size.toString())
        }

        binding.buttonSend.setOnClickListener {
            sharedViewModel.getUserName(binding.editTextChatInput.text.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }


}