package com.example.newninebank.model

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.newninebank.EnterAccountFragmentDirections
import com.example.newninebank.HomeFragmentDirections
import com.example.newninebank.R
import java.text.NumberFormat

class NineBankViewModel : ViewModel() {

    private val _testMoney = MutableLiveData(0.0)
    val testMoney: LiveData<String> = _testMoney.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _incomeValue = MutableLiveData(0.0)
    val incomeValue: LiveData<SpannableString> = _incomeValue.map {
        val editado: SpannableString =
            SpannableString.valueOf(NumberFormat.getCurrencyInstance().format(it))
        editado.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            it.toString().lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        editado

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

    private val _transactionHistoryList =
        MutableLiveData<MutableList<TransactionModel>>(mutableListOf())

    fun transformList(): List<TransactionModel> {
        return _transactionHistoryList.value!!.toList()
    }


    private val _userName = MutableLiveData("John")
    val userName: LiveData<String> = _userName

    fun onClickNavigate(fragment: Fragment, navToFragmentName: String) {
        lateinit var action: NavDirections
        val listOfFragments = fragment.resources.getTextArray(R.array.listOfFragments)

        when (navToFragmentName) {
            listOfFragments[0] -> action =
                HomeFragmentDirections.actionHomeFragmentToFinancialStatementFragment()

            listOfFragments[1] -> action =
                EnterAccountFragmentDirections.actionEnterAccountFragmentToHomeFragment()
        }

        return findNavController(fragment).navigate(action)
    }


    fun calSpent(spent: Double) {
        if (_accountCurrency.value!! >= spent && spent != 0.0) {
            _accountCurrency.value = _accountCurrency.value!!.minus(spent)
            addToTransactionsHistory(
                typeOfTransaction = "Transferência enviada",
                transactionContent = "ANGELA ROQUE\nPIX", value = spent,

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

}