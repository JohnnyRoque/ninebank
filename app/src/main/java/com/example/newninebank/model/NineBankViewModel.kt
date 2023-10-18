package com.example.newninebank.model

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.newninebank.R
import com.example.newninebank.ui.EnterAccountFragmentDirections
import com.example.newninebank.ui.HomeFragmentDirections
import com.example.newninebank.ui.TAG
import java.text.NumberFormat

class NineBankViewModel : ViewModel() {

    private val _incomeValue = MutableLiveData(0.0)
    val incomeValue: LiveData<String> = _incomeValue.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _savedMoney = MutableLiveData(0.0)
    val savedMoney: LiveData<String> = _savedMoney.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _accountCurrency = MutableLiveData(1000.0)
    val accountCurrency: LiveData<String> = _accountCurrency.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _availableLimit = MutableLiveData(0.0)
    val availableLimit: LiveData<String> = _availableLimit.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _currentBill = MutableLiveData(0.0)
    val currentBill: LiveData<String> = _currentBill.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName.map { s ->
        var formattedName = s
        if (!s.none()) {
            formattedName = s.replaceFirstChar { it.uppercase() }
        }
        formattedName
    }

    private val _transactionHistoryList =
        MutableLiveData<MutableList<TransactionModel>>(mutableListOf())


    private fun loadTextsOpenAccount(chatList: MutableList<OpenAccountModel>): MutableList<OpenAccountModel> {
        _openAccountChatList.postValue(chatList)
        return chatList
    }

    private val _userCpf = MutableLiveData<String>()
    val userCpf: LiveData<String> = _userCpf

    private val _userAcceptTerms: MutableLiveData<Boolean> = MutableLiveData()
    val userAcceptTerms: LiveData<Boolean> = _userAcceptTerms

    private val _userTypeOfAccount = MutableLiveData("")
    val userTypeOfAccount: LiveData<String> = _userTypeOfAccount


    private val _userEmail = MutableLiveData("")
    val userEmail: LiveData<String> = _userEmail

    private val _userPassword = MutableLiveData("")
    val userPassword: LiveData<String> = _userPassword


    private val chatList = mutableListOf<OpenAccountModel>()

    private val _openAccountChatList: MutableLiveData<List<OpenAccountModel>> = MutableLiveData()
    val openAccountChatList: LiveData<List<OpenAccountModel>> = _openAccountChatList

    private var _addNewTextCount = MutableLiveData(0)
    val addNewTextCount: LiveData<Int> = _addNewTextCount

    private fun addNewText() {

        while ((_addNewTextCount.value ?: 0) < 11) {

            _addNewTextCount.value = _addNewTextCount.value?.inc()

            when (_addNewTextCount.value) {
                1 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.hello_text,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op1")
                }

                2 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.welcome_text,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op2")
                }

                3 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.open_account_title,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op3")
                }

                4 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.open_account_name,
                            R.string.social_name_button_text,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op4")
                    break
                }

                5 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.open_account_cpf,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op5")
                    break
                }

                6 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.open_account_terms_text,
                            R.string.open_account_terms_button_text,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, "op6")
                    break
                }

                7 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            R.string.open_account_types_of_account,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null

                        )
                    )
                    break
                }

                8 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            text = R.string.open_account_enter_email,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    break
                }

                9 -> {
                    loadTextsOpenAccount(chatList).add(
                        OpenAccountModel(
                            text = R.string.open_account_enter_password,
                            null,
                            haveAButton = false,
                            isUserText = false,
                            null
                        )
                    )
                    Log.d(TAG, _addNewTextCount.value.toString())
                    break

                }
            }
        }
        Log.d(TAG, _addNewTextCount.value.toString())
    }

    fun getUserInput(input: String) {
        when (_addNewTextCount.value) {

            4 -> {
                _userName.value = input
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = true,
                        isUserText = true,
                        userText = input
                    )
                )
            }

            5 -> {
                _userCpf.value = input
                Log.d(TAG, (userCpf.value ?: "").toString())
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = false,
                        isUserText = true,
                        userText = input
                    )
                )
            }

            6 -> {
                _userAcceptTerms.value = true
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = false,
                        isUserText = true,
                        input
                    )
                )
            }

            7 -> {
                _userTypeOfAccount.value = input
                Log.d("testUserType", (userTypeOfAccount.value).toString())
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = false,
                        isUserText = true,
                        input
                    )
                )
            }

            8 -> {
                _userEmail.value = input
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = false,
                        isUserText = true,
                        input
                    )
                )
            }

            else -> {
                _userPassword.value = input
                loadTextsOpenAccount(chatList).add(
                    OpenAccountModel(
                        null,
                        null,
                        haveAButton = false,
                        isUserText = true,
                        userText = input
                    )
                )
            }
        }
        addNewText()
    }

    fun transformList(): List<TransactionModel> {
        return (_transactionHistoryList.value ?: listOf()).toList()
    }

    fun onClickNavigate(fragment: Fragment, navToFragmentName: String): Array<CharSequence> {
        lateinit var action: NavDirections
        val listOfFragments = fragment.resources.getTextArray(R.array.listOfFragments)

        when (navToFragmentName) {

            listOfFragments[0] -> action =
                HomeFragmentDirections.actionHomeFragmentToFinancialStatementFragment()

            listOfFragments[1] -> {
                action =
                    EnterAccountFragmentDirections.actionEnterAccountFragmentToHomeFragment()
            }

            listOfFragments[2] -> {
                eraseChat()
                addNewText()
                action =
                    EnterAccountFragmentDirections.actionEnterAccountFragmentToOpenAccountFragment()
            }
        }
        findNavController(fragment).navigate(action)
        return listOfFragments
    }

    fun calSpent(spent: Double) {
        if ((_accountCurrency.value ?: 0.0) >= spent && spent != 0.0) {
            _accountCurrency.value = _accountCurrency.value!!.minus(spent)
            addToTransactionsHistory(
                typeOfTransaction = "Transferência enviada",
                transactionContent = "ANGELA JACKSON\nPIX", value = spent,

                )
            Log.d("CalSpent", "Add a lista ${accountCurrency.value}")
        } else {
            Log.d("CalSpent", "Dinheiro insuficiente ${accountCurrency.value}")
        }
    }

    private fun addToTransactionsHistory(
        typeOfTransaction: String,
        transactionContent: String,
        value: Double = 0.0
    ): MutableList<TransactionModel> {
        val formattedValue = NumberFormat.getCurrencyInstance().format(value)
        _transactionHistoryList.value!!.add(
            TransactionModel(
                typeOfTransaction,
                transactionContent,
                formattedValue
            )
        )
        Log.d("CalSpent", "${_transactionHistoryList.value}")
        return _transactionHistoryList.value!!
    }

    fun eraseChat() {
        chatList.clear()
        _userAcceptTerms.value = false
        _userCpf.value = ""
        _addNewTextCount.value = 0
        _userName.value = ""
    }

    fun validateCpf(cpf: String): Boolean {
        if (cpf.isDigitsOnly()) {

            val cpfRegex = "^(?!.{9}(.)\\1).{11}\$"
            val lastDigit = cpf.last().digitToInt()
            val secondToLastDigit = cpf[9]
            var count = cpf.length
            val calTimes: (Int, Int) -> Int = { digit: Int, countL: Int -> digit.times(countL) }
            var sum = 0
            for (i in cpf) {
                if (count > 2) {
                    count--
                    sum += calTimes(i.digitToInt(), count)
                }
            }
            var digit1Result: Int = 11 - (sum % 11)
            if (digit1Result > 9) {
                digit1Result = 0
            }
            count = cpf.length
            sum = 0
            val newCPF = cpf.replaceRange(9, 11, digit1Result.toString())
            for (a in newCPF) {
                if (count >= 2) {
                    sum += calTimes(a.digitToInt(), count)
                    count--
                }
            }
            var digit2Result = 11 - (sum % 11)
            if (digit2Result > 9) {
                digit2Result = 0
            }
            val result: (Int, Int) -> Boolean = { digit1: Int, digit2: Int ->
                (digit1 == secondToLastDigit.digitToInt()) && digit2 == lastDigit && Regex(cpfRegex).matches(
                    cpf
                )
            }
            return result(digit1Result, digit2Result)
        }
        else{
            return false
        }
    }
}

