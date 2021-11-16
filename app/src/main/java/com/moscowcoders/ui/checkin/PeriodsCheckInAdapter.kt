package com.moscowcoders.ui.checkin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.moscowcoders.R
import com.moscowcoders.data.models.sport_objects.ui_format.UiPeriod

class PeriodsCheckInAdapter(private val onCLickListener: OnPeriodClickListener): RecyclerView.Adapter<PeriodsCheckInAdapter.PeriodViewHolder>() {

    private val TAG_PERIODS_ADAPTER = "MyPeriodsCheckInAdapter"

    private var periodsList = emptyList<UiPeriod>()

    private var clickedPosition: Int? = null
    private var listOfHolders = mutableListOf<PeriodViewHolder>()

    fun setList(list: List<UiPeriod>){
        periodsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        return PeriodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_period_rv, parent, false))
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {

        holder.bind(periodsList[position])

        if (!listOfHolders.contains(holder)){
            listOfHolders.add(holder)
        }

        if (position == clickedPosition){
            holder.setNewColors(R.color.white, R.color.black)
        } else {
            holder.setNewColors(R.color.black, R.color.white)
        }

        holder.itemView.setOnClickListener{
            clickedPosition = position
            for (holderInList in listOfHolders){
                holderInList.setNewColors(R.color.black, R.color.white)
            }

            holder.itemView.setBackgroundColor(holder.itemView.context.resources.getColor(R.color.black, null))
            holder.textView.setTextColor(holder.itemView.context.resources.getColor(R.color.white, null))


            onCLickListener.onClick(periodsList[position])
        }
    }

    override fun getItemCount(): Int {
        return periodsList.size
    }

    class PeriodViewHolder(newView: View): RecyclerView.ViewHolder(newView){

        private val TAG_PERIODS_ADAPTER = "MyPeriodViewHolder"

        val textView = itemView.findViewById<TextView>(R.id.id_open_time_textview)

        fun bind(newPeriod: UiPeriod){
            textView.text = newPeriod.open
        }

        fun setNewColors(@ColorRes textColor: Int, @ColorRes backColor: Int){
            Log.d(TAG_PERIODS_ADAPTER, "Вызван SetNewColors со значениями: textColor = $textColor, backColor = $backColor")
            textView.setTextColor(itemView.context.resources.getColor(textColor, null))
            itemView.setBackgroundColor(itemView.context.resources.getColor(backColor, null))
        }
    }

    interface OnPeriodClickListener{
        fun onClick(data: UiPeriod)
    }
}