package com.example.newninebank

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.setMargins
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.model.OpenAccountModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class TextRecyclerView(
    private val context: Context
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
        val materialCard: MaterialCardView = view.findViewById(R.id.text_material_card)
        val materialButton: MaterialButton = view.findViewById(R.id.open_account_user_button)
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

        val layoutParamsUser = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.END
            setMargins(8)

        }
        val layoutParamsChat = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.START
            setMargins(8)
        }



        val item = asyncDiff.currentList[position]
        when {
            item.isUserText -> {
                holder.materialCard.setCardBackgroundColor(context.getColor(R.color.md_theme_light_primaryContainer))
                holder.materialCard.layoutParams = layoutParamsUser
                holder.materialText.text = item.userText.toString().replaceFirstChar {
                    it.uppercaseChar()
                }
                holder.materialButton.visibility = GONE

            }

            item.haveAButton && !item.isUserText -> {
                item.text?.let { holder.materialText.setText(it) }
                holder.materialButton.visibility = VISIBLE
                holder.materialButton.setText(item.buttonText!!)
            }

            else -> {
                holder.materialCard.layoutParams =layoutParamsChat
                holder.materialCard.setCardBackgroundColor(context.getColor(R.color.md_theme_light_surfaceVariant))
                item.text?.let { holder.materialText.setText(it) }
            }

        }
    }
}