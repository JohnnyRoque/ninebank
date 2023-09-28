package com.example.newninebank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.model.DataModel
import com.example.newninebank.model.DataSet
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class TextRecyclerView( private val context: Context,private val dataSetList: List<DataModel> = DataSet().loadTexts()): RecyclerView.Adapter<TextRecyclerView.TextRecyclerViewHolder>() {
    class TextRecyclerViewHolder(view: View):RecyclerView.ViewHolder(view){
        val materialText: MaterialTextView = view.findViewById(R.id.material_text_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_design,parent,false)
        return TextRecyclerViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return dataSetList.size
    }

    override fun onBindViewHolder(holder: TextRecyclerViewHolder, position: Int) {
        val item = dataSetList[position]
        holder.materialText.text = context.getText(item.name)

    }
}