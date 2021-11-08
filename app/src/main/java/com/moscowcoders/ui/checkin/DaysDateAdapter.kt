package com.moscowcoders.ui.checkin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moscowcoders.R

class DaysDateAdapter: RecyclerView.Adapter<DaysDateAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_for_edittexts_menu, parent, false))
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(10)
    }

    override fun getItemCount(): Int {
        return 10
    }

    class DayViewHolder(newView: View): RecyclerView.ViewHolder(newView){
        private val textView = itemView.findViewById<TextView>(R.id.id_textview_item)

        fun bind(a: Int){
            textView.textSize = 25F
            textView.text = a.toString()
        }
    }
}