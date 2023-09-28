package com.example.newninebank.model

import com.example.newninebank.R

class DataSet {
    fun loadButtons(): List<DataModel> {
        return listOf(
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24),
            DataModel(R.string.app_name, R.drawable.baseline_arrow_forward_ios_24)

        )
    }

    fun loadTexts(): List<DataModel> {
        return listOf(
            DataModel(R.string.ninebank_slogan),
            DataModel(R.string.ninebank_slogan),
            DataModel(R.string.ninebank_slogan))
    }
}