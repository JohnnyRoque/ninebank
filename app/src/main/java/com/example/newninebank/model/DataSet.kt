package com.example.newninebank.model

import com.example.newninebank.R

class DataSet {
    fun loadButtons(): List<DataModel> {
        return listOf(
            DataModel(R.string.recharge_phone_text, R.drawable.smartphone_24px),
            DataModel(R.string.payments_text, R.drawable.barcode_24px),
            DataModel(R.string.pix_text, R.drawable.qr_code_2_24px),
            DataModel(R.string.bank_loan_text, R.drawable.paid_24px),
            DataModel(R.string.savings_text, R.drawable.savings_24px),
            DataModel(R.string.transfer_text, R.drawable.currency_exchange_24px),
            DataModel(R.string.financial_statement, R.drawable.account_balance_24px),
            DataModel(R.string.investments_text, R.drawable.monitoring_24px)

        )
    }

    fun loadTexts(): List<DataModel> {
        return listOf(
            DataModel(R.string.ninebank_slogan),
            DataModel(R.string.ninebank_slogan),
            DataModel(R.string.ninebank_slogan)
        )
    }




}