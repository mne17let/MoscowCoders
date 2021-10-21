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
import com.moscowcoders.data.models.SportObjectModel

// Класс адаптера для списка спортивных объектов

class ListSportObjectsAdapter(private val clickListener: OnClickListener): RecyclerView.Adapter<ListSportObjectsAdapter.SportObjectViewHolder>() {

    // Тег для логов
    private val TAG_ADAPTER = "SportObjectsListAdapter"

    private var list = emptyList<SportObjectModel>()

    fun setList(newList: List<SportObjectModel>){
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

        fun bind(data: SportObjectModel){
            status.text = data.isOpen.toString()
            title.text = data.name
            Glide.with(imageView).load(data.image_url).into(imageView)

            makeRequestButton.setOnClickListener {
                data.id?.let { id_not_null -> clickListener.onClick(id_not_null)
                    Log.d("SportObjectsListAdapter", "В адаптере передано: $id_not_null")
                }
            }
        }
    }

    interface OnClickListener{
        fun onClick(id: String)
    }
}