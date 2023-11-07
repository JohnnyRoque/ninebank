package com.example.newninebank.ui.open_account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.R
import com.example.newninebank.model.TypesOfAccount
import com.example.newninebank.model.TypesOfAccountModel
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class TypesOfAccountAdapter( private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<TypesOfAccountAdapter.TypesOfAccountViewHolder>() {
    val typesAsyncDiff =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<TypesOfAccountModel>() {

            override fun areItemsTheSame(
                oldItem: TypesOfAccountModel,
                newItem: TypesOfAccountModel
            ): Boolean {
                return oldItem.titleText == newItem.titleText
            }

            override fun areContentsTheSame(
                oldItem: TypesOfAccountModel,
                newItem: TypesOfAccountModel
            ): Boolean {
                return oldItem == newItem
            }
        })

    class TypesOfAccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val accountTypeImage: ShapeableImageView = view.findViewById(R.id.type_of_account_image)
        val typeOfAccountTitle: MaterialTextView = view.findViewById(R.id.type_of_account_head)
        val typeOfAccountBody: MaterialTextView = view.findViewById(R.id.type_of_account_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesOfAccountViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.types_of_account_recycler_design, parent, false)
        return TypesOfAccountViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return typesAsyncDiff.currentList.size
    }

    override fun onBindViewHolder(holder: TypesOfAccountViewHolder, position: Int) {
        val item = typesAsyncDiff.currentList[position]

        holder.accountTypeImage.setImageResource(item.image)
        holder.typeOfAccountTitle.setText(item.titleText)
        holder.typeOfAccountBody.setText(item.bodyText)
        holder.itemView.setOnClickListener {

            when (item.titleText) {
                R.string.individual_account_title_text -> {
                    onItemClick(TypesOfAccount.INDIVIDUAL_ACCOUNT)
                }
                R.string.teen_account_title_text -> {
                    onItemClick(TypesOfAccount.TEEN_ACCOUNT)
                }

                R.string.joint_account_title_text -> { onItemClick(TypesOfAccount.JOINT_ACCOUNT)

                }
            }
        }
    }
}