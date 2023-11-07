package com.example.newninebank.ui.financial_statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.R
import com.example.newninebank.model.TransactionModel
import com.google.android.material.textview.MaterialTextView


class TransactionHistoryRecycler(
    private val dataSet: List<TransactionModel>
) : RecyclerView.Adapter<TransactionHistoryRecycler.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val typeOfTransactionVH: MaterialTextView =
            view.findViewById(R.id.type_of_transaction_textview)
        val transactionContentVH: MaterialTextView =
            view.findViewById(R.id.transaction_content_textview)
        val transactionValueVH: MaterialTextView =
            view.findViewById(R.id.transaction_value_textview)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_design, parent, false)
        return TransactionViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = dataSet[position]
        holder.typeOfTransactionVH.text = item.typeOfTransaction
        holder.transactionContentVH.text = item.transactionContent
        holder.transactionValueVH.text = item.value

    }
}