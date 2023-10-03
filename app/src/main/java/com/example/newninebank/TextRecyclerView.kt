package com.example.newninebank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.model.DataModel
import com.example.newninebank.model.DataSet
import com.example.newninebank.model.OpenAccountModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class TextRecyclerView(
    private val dataSetList: List<DataModel> = DataSet().loadTexts(),
) : RecyclerView.Adapter<TextRecyclerView.TextRecyclerViewHolder>() {
    val asyncDiff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<OpenAccountModel>() {
        override fun areItemsTheSame(
            oldItem: OpenAccountModel,
            newItem: OpenAccountModel
        ): Boolean {
            return oldItem.userText == newItem.userText
        }

        override fun areContentsTheSame(
            oldItem: OpenAccountModel,
            newItem: OpenAccountModel
        ): Boolean {
            return oldItem == newItem
        }

    })

    class TextRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val materialButton: MaterialButton = view.findViewById(R.id.button_social_name)
        val materialText: MaterialTextView = view.findViewById(R.id.material_text_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_design, parent, false)
        return TextRecyclerViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return asyncDiff.currentList.size
    }

    override fun onBindViewHolder(holder: TextRecyclerViewHolder, position: Int) {

        val item = asyncDiff.currentList[position]
        if (item.text == null) {
            holder.materialText.text = item.userText
        } else {
            holder.materialText.setText(item.text)
        }
        if (item.haveAButton) {
            holder.materialButton.visibility = VISIBLE
            holder.materialButton.setText(item.buttonText!!)
        }
    }
}