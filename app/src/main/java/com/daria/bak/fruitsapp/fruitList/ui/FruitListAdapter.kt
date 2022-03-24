package com.daria.bak.fruitsapp.fruitList.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.fruitList.business.Fruit

class FruitListAdapter(var dataSet: ArrayList<Fruit>):  RecyclerView.Adapter<FruitListAdapter.ViewHolder>(){
    var handler: ((Fruit) -> Unit)? = null

    class ViewHolder(view: View, handler: ((Fruit) -> Unit)?) : RecyclerView.ViewHolder(view) {
        val typeView: TextView = view.findViewById(R.id.type_text)
        var price: String = ""
        var weight: String = ""
        lateinit var fruit: Fruit
        init {
            view.setOnClickListener{
                handler?.invoke(fruit)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fruit_list_item_layout, viewGroup, false)

        return ViewHolder(view, handler)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.typeView.text = dataSet.get(position).type
        viewHolder.price = dataSet[position].price.toString()
        viewHolder.weight = dataSet[position].weight.toString()
        viewHolder.fruit = dataSet[position]

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setTapHandler(handler: (Fruit) -> Unit){
        this.handler = handler
    }
}