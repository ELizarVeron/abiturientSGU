package com.android.abiturientsgu.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.abiturientsgu.databinding.SpecialityItemBinding
import com.android.abiturientsgu.domain.models.Specialty

class SpecialityAdapter(
    private val context: Context,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<SpecialityAdapter.ProductsViewHolder>() {

    var itemList: List<Specialty> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = SpecialityItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size ?: 0
    }


    inner class ProductsViewHolder(
        private val productItemBinding: SpecialityItemBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(productItemBinding.root),
        View.OnClickListener {

        fun bind(item: Specialty) {
            val id = itemList[adapterPosition].id
            productItemBinding.specItemLinear.setOnClickListener(this)
            productItemBinding.specTitle.text = item.specialty

        }

        @SuppressLint("SuspiciousIndentation")
        override fun onClick(p0: View?) {
            val id = itemList[adapterPosition].id
            onItemClick(id)
        }


    }
}

