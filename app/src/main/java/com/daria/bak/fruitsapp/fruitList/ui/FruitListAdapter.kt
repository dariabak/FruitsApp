package com.daria.bak.fruitsapp.fruitList.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.fruitList.business.Fruit

class FruitListAdapter(var dataSet: ArrayList<Fruit>):  RecyclerView.Adapter<FruitListAdapter.ViewHolder>(){
    var handler: ((Fruit) -> Unit)? = null

    class ViewHolder(view: View, handler: ((Fruit) -> Unit)?) : RecyclerView.ViewHolder(view) {
        val typeView: TextView = view.findViewById(R.id.type_text)

        lateinit var fruit: Fruit
        init {
            view.setOnClickListener{
                handler?.invoke(fruit)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fruit_list_item_layout, viewGroup, false)

        return ViewHolder(view, handler)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.typeView.text = dataSet.get(position).type
        viewHolder.fruit = dataSet[position]

    }

    override fun getItemCount() = dataSet.size

    fun setTapHandler(handler: (Fruit) -> Unit){
        this.handler = handler
    }
}