package com.example.newninebank

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.newninebank.databinding.FragmentOpenAccountBinding
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OpenAccountFragment : Fragment() {
    private var _binding: FragmentOpenAccountBinding? = null
    val binding get() = _binding!!
    private lateinit var recyclerChat: TextRecyclerView
    private val sharedViewModel: NineBankViewModel by activityViewModels()
    private lateinit var inputMethodManager: InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle(R.string.open_account_dialog_title)
                    .setMessage(R.string.open_account_dialog_message)
                setPositiveButton(R.string.positive_text) { _, _ ->
                    sharedViewModel.eraseChat()
                    NavHostFragment.findNavController(requireParentFragment()).navigateUp()
                }
                setNegativeButton(
                    R.string.negative_text
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                    .create()
                    .show()
            }

        }
        super.onCreate(savedInstanceState)

    }

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
        inputMethodManager= requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val userInputEditText = binding.editTextChatInput.text
        recyclerChat = TextRecyclerView(requireContext())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            textRecyclerAdapter = recyclerChat
            inputText = userInputEditText
        }
        sharedViewModel.openAccountChatList.observe(viewLifecycleOwner) {
            recyclerChat.asyncDiff.submitList(it)
            changeUserInput(recyclerChat.asyncDiff.currentList.size)
            recyclerChat.notifyItemInserted(it.size + 1)
            Log.d(TAG, " async List = ${recyclerChat.asyncDiff.currentList.size}")
        }


//        binding.buttonSend.setOnClickListener {
//            sharedViewModel.getUserInput(userInputEditText.text.toString())
//        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun verifyUserInput(input: Editable, hasLineLimit: Int = 0) {
        binding.buttonSend.isEnabled = input.isNotEmpty() && input.length >= hasLineLimit

    }

    private fun changeUserInput(count: Int) {
        when (count) {
            4 -> {
                binding.buttonSend.visibility = VISIBLE
                binding.editTextChatInput.visibility = VISIBLE
                binding.editTextChatInput.setHint(R.string.open_account_enter_name_hint)
                binding.editTextChatInput.doAfterTextChanged {
                    verifyUserInput(it!!)
                }
            }

            6 -> {
                binding.editTextChatInput.filters += InputFilter.LengthFilter(11)
                binding.editTextChatInput.text.clear()
                binding.editTextChatInput.inputType = InputType.TYPE_CLASS_NUMBER
                binding.editTextChatInput.setHint(R.string.open_account_enter_cpf_hint)
                binding.editTextChatInput.doAfterTextChanged {
                    verifyUserInput(it!!, 11)
                }
                binding.buttonSend.setOnClickListener {
                    sharedViewModel.getUserInput(binding.editTextChatInput.text.toString())
                    inputMethodManager.hideSoftInputFromWindow(requireView().windowToken,0)

                }

            }

            8 -> {
                binding.userInputButton.visibility = VISIBLE
                binding.buttonSend.visibility = GONE
                binding.editTextChatInput.visibility = GONE
                binding.userInputButton.setOnClickListener {
                    findNavController().navigate(
                        OpenAccountFragmentDirections.actionOpenAccountFragmentToHomeFragment()
                    )
                }
            }
        }
    }

}