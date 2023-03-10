package com.android.abiturientsgu.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.abiturientsgu.databinding.TagItemBinding


class HashTagAdapter(
    private val context: Context,
    private val onButtonDeleteClick: (String) -> Unit
) :
    RecyclerView.Adapter<HashTagAdapter.HashTagsViewHolder>() {

    var itemList: List<String> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashTagsViewHolder {
        val binding = TagItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HashTagsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HashTagsViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size ?: 0
    }


    inner class HashTagsViewHolder(
        private val tagItemBinding: TagItemBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(tagItemBinding.root),
        View.OnClickListener {

        fun bind(item: String) {
            // val id = itemList[adapterPosition].id
            tagItemBinding.imgDeleteHashtag.setOnClickListener(this)
            tagItemBinding.tvHashtag.text = item


        }

        @SuppressLint("SuspiciousIndentation")
        override fun onClick(p0: View?) {
            /* val id = itemList[adapterPosition].id
             onItemClick(id)*/
            onButtonDeleteClick(itemList[adapterPosition])
        }


    }
}

