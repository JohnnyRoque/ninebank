package com.example.newninebank.ui.home_Fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.R
import com.example.newninebank.model.DataModel
import com.example.newninebank.model.DataSet
import com.example.newninebank.ui.TAG
import com.example.newninebank.util.Routes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class NineBankRecyclerView(
    private val context: Context,
    private val dataSet: List<DataModel> = DataSet().loadButtons(),
    private val onClickNavigateDestination: (String) -> Unit
) : RecyclerView.Adapter<NineBankRecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buttonName: MaterialTextView = view.findViewById(R.id.button_text)
        val floatingActionButton: FloatingActionButton = view.findViewById(R.id.home_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.buttons_design, parent, false)
        return ViewHolder(view = layoutInflater)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.buttonName.text = context.resources.getString(item.name)
        holder.floatingActionButton.setImageResource(item.image)
        holder.floatingActionButton.setOnClickListener {
            when (item.route) {
                Routes.FINANCIAL_STATEMENT -> {
                    Log.d(TAG,item.route)
                    onClickNavigateDestination(Routes.FINANCIAL_STATEMENT)
                }
            }
        }
    }
}