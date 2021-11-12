package com.moscowcoders.ui.checkin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moscowcoders.R
import com.moscowcoders.data.models.sport_objects.ui_format.UiPeriod

class PeriodsCheckInAdapter: RecyclerView.Adapter<PeriodsCheckInAdapter.PeriodViewHolder>() {

    private var periodsList = emptyList<UiPeriod>()

    fun setList(list: List<UiPeriod>){
        periodsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        return PeriodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_period_rv, parent, false))
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        holder.bind(periodsList[position])
    }

    override fun getItemCount(): Int {
        return periodsList.size
    }

    class PeriodViewHolder(newView: View): RecyclerView.ViewHolder(newView){
        private val textView = itemView.findViewById<TextView>(R.id.id_open_time_textview)

        fun bind(newPeriod: UiPeriod){
            textView.text = newPeriod.open
        }
    }
}