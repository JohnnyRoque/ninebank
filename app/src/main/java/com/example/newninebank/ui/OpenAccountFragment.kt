package com.example.newninebank.ui

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.newninebank.ChatRecyclerAdapter
import com.example.newninebank.ModalBottomSheet
import com.example.newninebank.R
import com.example.newninebank.databinding.FragmentOpenAccountBinding
import com.example.newninebank.model.NineBankViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class OpenAccountFragment : Fragment() {
    private var _binding: FragmentOpenAccountBinding? = null


    val binding get() = _binding!!
    private lateinit var recyclerChat: ChatRecyclerAdapter
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
        inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val userInputEditText = binding.editTextChatInput
        recyclerChat = ChatRecyclerAdapter(requireContext())

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            textRecyclerAdapter = recyclerChat
            inputText = userInputEditText.text
        }
        sharedViewModel.openAccountChatList.observe(viewLifecycleOwner) {
            recyclerChat.asyncDiff.submitList(it)
            changeUserInput(sharedViewModel.addNewTextCount.value ?: 0, userInputEditText)
            recyclerChat.notifyItemInserted(it.size + 1)
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun verifyUserInput(input: String, hasLineMinimal: Int = 0, isCpf: Boolean) {
        if (isCpf) {
            binding.buttonSend.isEnabled =
                input.isNotEmpty() && input.length >= hasLineMinimal && sharedViewModel.validateCpf(
                    input
                )
        } else {
            binding.buttonSend.isEnabled = input.isNotEmpty() && input.length >= hasLineMinimal
        }
    }

    private fun changeUserInput(count: Int, userEditText: EditText) {
        when (count) {
            4 -> {
                /** Enter Name*/
                binding.buttonSend.visibility = VISIBLE
                userEditText.visibility = VISIBLE
                userEditText.setHint(R.string.open_account_enter_name_hint)
                userEditText.doAfterTextChanged {
                    verifyUserInput(it.toString(), isCpf = false)
                }
            }

            5 -> {
                /** Enter CPF*/

                userEditText.text.clear()
                userEditText.filters += InputFilter.LengthFilter(11)
                userEditText.inputType = InputType.TYPE_CLASS_NUMBER
                userEditText.setHint(R.string.open_account_enter_cpf_hint)
                userEditText.doAfterTextChanged {
                    verifyUserInput(it.toString(), isCpf = true, hasLineMinimal = 11)

                }
            }

            6 -> {
                /** Accept terms of use and privacy*/

                inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
                binding.userInputButton.visibility = VISIBLE
                binding.buttonSend.visibility = GONE
                userEditText.visibility = GONE
                binding.userInputButton.setOnClickListener {
                    sharedViewModel.getUserInput(getString(R.string.right_emoji))
                }
            }

            7 -> {
                /** Choose a type of account*/

                binding.userInputButton.setText(R.string.select_type_of_account_text)
                binding.userInputButton.setOnClickListener {
                    createBottomSheet()
                }
            }

            8 -> {
                /**Enter an E-MAIL*/
                userEditText.text.clear()
                userEditText.filters = arrayOf(InputFilter.LengthFilter(Int.MAX_VALUE))
                binding.userInputButton.visibility = GONE
                binding.buttonSend.visibility = VISIBLE
                userEditText.visibility = VISIBLE
                userEditText.inputType = InputType.TYPE_CLASS_TEXT
                userEditText.setHint(R.string.open_account_enter_email)
                userEditText.doAfterTextChanged {
                    verifyUserInput(it.toString(), isCpf = false)
                }

            }

            9 -> {
                userEditText.text.clear()
                userEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                userEditText.setHint(R.string.open_account_enter_password)
                userEditText.doAfterTextChanged {
                    verifyUserInput(it.toString(), isCpf = false)
                }
            }
        }
    }

    private fun createBottomSheet() {
        ModalBottomSheet().show(parentFragmentManager, ModalBottomSheet.MODAL_TAG)
    }

}