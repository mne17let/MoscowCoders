package com.moscowcoders.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moscowcoders.R
import com.moscowcoders.data.models.sport_objects.SportObjectModel
import com.moscowcoders.data.models.sport_objects.ui_format.UiSportObject
import com.moscowcoders.ui.server_listeners.TimeHelper

// Класс адаптера для списка спортивных объектов

class ListSportObjectsAdapter(private val clickListener: OnClickListener, ): RecyclerView.Adapter<ListSportObjectsAdapter.SportObjectViewHolder>() {

    // Тег для логов
    private val TAG_ADAPTER = "SportObjectsListAdapter"

    private var list = emptyList<UiSportObject>()

    fun setList(newList: List<UiSportObject>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport_objects_constraint, parent, false)
        return SportObjectViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: SportObjectViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    class SportObjectViewHolder(newView: View,
                                private val clickListener: OnClickListener
    ): RecyclerView.ViewHolder(newView){

        private val status: TextView = itemView.findViewById(R.id.id_status_item_sport_object)
        private val title: TextView = itemView.findViewById(R.id.id_title_item_list_sport_objects)
        private val imageView: ImageView = itemView.findViewById(R.id.id_photo_item_sport_object)
        private val makeRequestButton: Button = itemView.findViewById(R.id.id_button_make_request_item_sport_object)

        fun bind(data: UiSportObject){
            title.text = data.name
            Glide.with(imageView).load(data.image_url).into(imageView)

            val currentTime = TimeHelper().getCurrentTime()

            var isOpen = false

            for (day in data.days){
                if(day.dateLong < currentTime){
                    for (period in day.listOfPeriods){
                        if (currentTime > period.longTimeOpen && currentTime < period.longTimeClose){
                            status.text = "Доступно"

                            isOpen = true
                            status.setBackgroundColor(itemView.context.resources.getColor(R.color.sport_object_available, null))
                        }
                    }
                }
            }

            if (!isOpen){
                status.text = "Недоступно"
                status.setBackgroundColor(itemView.context.resources.getColor(R.color.sport_object_unavailable, null))
            }

            makeRequestButton.setOnClickListener {
                data.id.let { id_not_null -> clickListener.onClick(id_not_null)
                    Log.d("SportObjectsListAdapter", "В адаптере передано: $id_not_null")
                }
            }
        }
    }

    interface OnClickListener{
        fun onClick(id: String)
    }
}