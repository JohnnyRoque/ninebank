package com.example.newninebank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.model.DataModel
import com.example.newninebank.model.DataSet
import com.example.newninebank.model.OpenAccountModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class TextRecyclerView(
    private val context: Context,
    private val dataSetList: List<DataModel> = DataSet().loadTexts(),
    private val dataSetOpenAccount: List<OpenAccountModel>?
) : RecyclerView.Adapter<TextRecyclerView.TextRecyclerViewHolder>() {
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
        return if (dataSetOpenAccount.isNullOrEmpty()) {
            dataSetList.size

        } else {
            dataSetOpenAccount.size
        }
    }

    override fun onBindViewHolder(holder: TextRecyclerViewHolder, position: Int) {
        when {

            !dataSetOpenAccount.isNullOrEmpty() -> {
                val item = dataSetOpenAccount[position]
                if(item.text == null){
                    holder.materialText.text = item.userText
                }else {
                    holder.materialText.text = item.text.let { context.getText(it) }
                }
                if (item.haveAButton) {
                    holder.materialButton.visibility = VISIBLE
                    holder.materialButton.setText(item.buttonText!!)
                }
            }

            else -> {
                val item = dataSetList[position]
                holder.materialText.text = context.getText(item.name)
            }
        }
    }
}