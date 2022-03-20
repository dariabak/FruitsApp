package com.daria.bak.fruitsapp.fruitList.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.business.Fruit

class FruitListAdapter(private val dataSet: ArrayList<Fruit>, fragment: Fragment):  RecyclerView.Adapter<FruitListAdapter.ViewHolder>(){
    val fragment: Fragment = fragment
    class ViewHolder(view: View, fragment: Fragment) : RecyclerView.ViewHolder(view) {
        var typeView: TextView

        init {
            typeView = view.findViewById(R.id.type_text)
//            view.setOnClickListener{
//                handler?.invoke(id)
//            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fruit_list_layout, viewGroup, false)

        return ViewHolder(view, fragment)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.typeView.text = dataSet.get(position).type
//        viewHolder.dateTextView.text = dataSet.get(position).update
//        viewHolder.numberOfTeamsTextView.text = dataSet.get(position).numberOfTeams.toString()
//        viewHolder.id = dataSet.get(position).id

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

//    fun setTapHandler(handler: (String) -> Unit){
//        this.handler = handler
//    }
}