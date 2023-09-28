package com.example.newninebank.model

import androidx.annotation.StringRes

data class DataModel(
    @StringRes val name: Int,
    var image: Int = 0
)

data class TransactionModel(
    val typeOfTransaction: String,
    val transactionContent:String,
    val value :String
    )
