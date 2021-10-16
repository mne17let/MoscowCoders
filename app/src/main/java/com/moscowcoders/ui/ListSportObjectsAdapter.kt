package com.moscowcoders.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moscowcoders.R
import com.moscowcoders.data.SportObjectModel

class ListSportObjectsAdapter: RecyclerView.Adapter<SportObjectViewHolder>() {

    private var list = emptyList<SportObjectModel>()

    fun setList(newList: List<SportObjectModel>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport_objects_constraint, parent, false)
        return SportObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportObjectViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class SportObjectViewHolder(newView: View): RecyclerView.ViewHolder(newView){

    private val status: TextView = itemView.findViewById(R.id.id_status_item_sport_object)
    private val title: TextView = itemView.findViewById(R.id.id_title_item_list_sport_objects)

    fun bind(data: SportObjectModel){
        status.text = data.isOpen.toString()
        title.text = data.name
    }
}