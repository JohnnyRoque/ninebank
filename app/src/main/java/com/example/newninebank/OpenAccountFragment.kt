package com.example.newninebank

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
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
        sharedViewModel.testInc()

        val userInputEditText =binding.editTextChatInput
        recyclerChat = TextRecyclerView(requireContext())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            textRecyclerAdapter = recyclerChat
        }
        sharedViewModel.openAccountChatList.observe(viewLifecycleOwner) {
            recyclerChat.asyncDiff.submitList(it)
            changeUserInput(recyclerChat.asyncDiff.currentList.size)
            recyclerChat.notifyItemInserted(it.size + 1)
            Log.d(TAG," async List = ${recyclerChat.asyncDiff.currentList.size}")
        }

        userInputEditText.doAfterTextChanged {
            verifyUserInput(it!!)
        }

        binding.buttonSend.setOnClickListener {
            sharedViewModel.getUserName(userInputEditText.text.toString())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun verifyUserInput(input: Editable){
         binding.buttonSend.isEnabled = input.isNotEmpty()
    }
    private fun changeUserInput(count:Int){
        when(count){
            4 ->{
                binding.layoutUserInput.visibility = VISIBLE
                binding.buttonSend.visibility = VISIBLE
                binding.editTextChatInput.visibility = VISIBLE
                binding.editTextChatInput.setHint(R.string.open_account_enter_name_hint)
            }
            6->{binding.editTextChatInput.inputType= InputType.TYPE_CLASS_NUMBER
                binding.editTextChatInput.setHint(R.string.open_account_enter_cpf_hint)

            }
        }
    }

}