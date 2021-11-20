package com.sportrgu.fragment_check_in.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.sportrgu.R
import com.sportrgu.data.models.sport_objects.ui_format.UiPeriod

class PeriodsCheckInAdapter(private val onCLickListener: OnPeriodClickListener): RecyclerView.Adapter<PeriodsCheckInAdapter.PeriodViewHolder>() {

    private val TAG_PERIODS_ADAPTER = "MyPeriodsCheckInAdapter"

    private var periodsList = emptyList<UiPeriod>()

    // Clicked position
    private var clickedPosition: Int? = null
    private var previousClickedPosition: Int? = null

    // Clicked holder
    private var clickedHolder: PeriodViewHolder? = null
    private var previousClickedHolder: PeriodViewHolder? = null

    // private var listOfHolders = mutableListOf<PeriodViewHolder>()

    fun setList(list: List<UiPeriod>){
        periodsList = list
        notifyDataSetChanged()
    }

    fun resetClickedItems(){
        clickedHolder?.setUnClickedColors(R.color.white, R.color.my_gray)

        clickedHolder = null
        previousClickedHolder = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        return PeriodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_period_rv, parent, false))
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {

        /*if (!listOfHolders.contains(holder)){
            listOfHolders.add(holder)
        }*/

        /*if (position == clickedPosition){
            holder.setNewColors(R.color.white, R.color.black)
        } else {
            holder.setNewColors(R.color.black, R.color.white)
        }*/

        if (holder == clickedHolder) {
            holder.setClickedColors(R.color.white, R.color.black)
        }

        holder.itemView.setOnClickListener{

            Log.d(TAG_PERIODS_ADAPTER, "Вызван holder.itemView.setOnClickListener")

            previousClickedPosition = clickedPosition
            clickedPosition = position

            previousClickedHolder = clickedHolder
            clickedHolder = holder

            val black = holder.itemView.context.resources.getColor(R.color.black, null)
            val white = holder.itemView.context.resources.getColor(R.color.white, null)
            val gray = holder.itemView.context.resources.getColor(R.color.my_gray, null)

            previousClickedHolder?.setUnClickedColors(R.color.white, R.color.my_gray)
            holder.setClickedColors(R.color.white, R.color.black)

            onCLickListener.onClick(periodsList[position])
        }

        holder.bind(periodsList[position])
    }

    override fun getItemCount(): Int {
        return periodsList.size
    }

    class PeriodViewHolder(newView: View): RecyclerView.ViewHolder(newView){

        private val TAG_PERIODS_ADAPTER = "MyPeriodViewHolder"

        private val textView = itemView.findViewById<TextView>(R.id.id_open_time_textview)
        private val linear = itemView.findViewById<LinearLayout>(R.id.id_item_period_linear)

        fun bind(newPeriod: UiPeriod){
            textView.text = newPeriod.open
        }

        fun setClickedColors(@ColorRes textColor: Int, @ColorRes backColor: Int){
            Log.d(TAG_PERIODS_ADAPTER, "Вызван setClickedColors со значениями: textColor = $textColor, backColor = $backColor")
            textView.setTextColor(itemView.context.resources.getColor(textColor, null))
            textView.setBackgroundColor(itemView.context.resources.getColor(backColor, null))
        }

        fun setUnClickedColors(@ColorRes textColor: Int, @ColorRes backColor: Int){
            Log.d(TAG_PERIODS_ADAPTER, "Вызван setUnClickedColors со значениями: textColor = $textColor, backColor = $backColor")
            textView.setTextColor(itemView.context.resources.getColor(textColor, null))
            textView.setBackgroundColor(itemView.context.resources.getColor(backColor, null))
        }
    }

    interface OnPeriodClickListener{
        fun onClick(data: UiPeriod)
    }
}