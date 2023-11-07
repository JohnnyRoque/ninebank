package com.example.newninebank.ui.home_Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.setMargins
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.R
import com.example.newninebank.model.DataModel
import com.google.android.material.textview.MaterialTextView

class TextRecyclerView : RecyclerView.Adapter<TextRecyclerView.ChatRecyclerViewHolder>() {

    val asyncDiff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(
            oldItem: DataModel,
            newItem: DataModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: DataModel,
            newItem: DataModel
        ): Boolean {
            return oldItem == newItem
        }

    })

    class ChatRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val frameLayout: FrameLayout = view.findViewById(R.id.frame_layout_home)
        val materialText: MaterialTextView = view.findViewById(R.id.material_text_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_design, parent, false)
        return ChatRecyclerViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return asyncDiff.currentList.size
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewHolder, position: Int) {
        val item = asyncDiff.currentList[position]

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0)
        }
        holder.frameLayout.layoutParams = layoutParams
        holder.materialText.setText(item.name)
    }
}