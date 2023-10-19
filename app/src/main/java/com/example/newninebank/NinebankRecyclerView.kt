package com.example.newninebank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newninebank.model.DataModel
import com.example.newninebank.model.DataSet
import com.example.newninebank.ui.HomeFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class NineBankRecyclerView(val fragment:Fragment,private val context: Context, private val dataSet: List<DataModel> = DataSet().loadButtons()) :
    RecyclerView.Adapter<NineBankRecyclerView.ViewHolder>() {

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
        holder.buttonName.text = context.resources.getText(item.name)
        holder.floatingActionButton.setImageResource(item.image)
        holder.floatingActionButton.setOnClickListener {
            when (holder.buttonName.text){
                context.resources.getStringArray(R.array.listOfFragments)[3]->{
                    findNavController(fragment).navigate(HomeFragmentDirections.actionHomeFragmentToFinancialStatementFragment())
                }

            }
        }
    }
}