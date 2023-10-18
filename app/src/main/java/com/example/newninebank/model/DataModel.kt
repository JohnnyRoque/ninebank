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

data class OpenAccountModel(
    val text: Int?,
    var buttonText: Int?,
    val haveAButton:Boolean,
    val isUserText:Boolean,
    var userText: String?
)


data class TypesOfAccountModel(
    val titleText: Int,
    val image: Int,
    val bodyText : Int,

)