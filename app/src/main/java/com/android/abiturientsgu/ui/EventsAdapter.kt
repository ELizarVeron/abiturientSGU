package com.android.abiturientsgu.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.abiturientsgu.databinding.EventItemBinding
import com.android.abiturientsgu.domain.models.Event


class EventsAdapter(
    private val context: Context,
    private val onButtonClick: (Int) -> Unit
) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    var itemList: List<Event> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return EventsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size ?: 0
    }


    inner class EventsViewHolder(
        private val eventItemBinding: EventItemBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(eventItemBinding.root),
        View.OnClickListener {

        fun bind(item: Event) {
            val id = itemList[adapterPosition].id
            eventItemBinding.buttonDo.setOnClickListener(this)
            eventItemBinding.tvEventTitle.text = item.eventName
            eventItemBinding.tvDate.text = item.date
            eventItemBinding.tvPlace.text = item.place
            eventItemBinding.tvTags.text = item.themes.joinToString(" ") { "#$it" }

        }

        @SuppressLint("SuspiciousIndentation")
        override fun onClick(p0: View?) {
            /* val id = itemList[adapterPosition].id
             onItemClick(id)*/
        }


    }
}

