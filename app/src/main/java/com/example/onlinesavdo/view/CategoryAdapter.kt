package com.example.onlinesavdo.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.databinding.CategoryItemLayoutBinding
import com.example.onlinesavdo.model.CategoryModel
import com.example.onlinesavdo.utils.Constant


interface CategoryAdapterCallback{
    fun onClick(item: CategoryModel)
}

class CategoryAdapter(val items: List<CategoryModel>, val callback: CategoryAdapterCallback): RecyclerView.Adapter<CategoryAdapter.ItemHolder>(){

   inner class ItemHolder(val binding: CategoryItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            items.forEach {
                item.checked = false
            }
            item.checked = true
            callback.onClick(item)
            notifyDataSetChanged()
        }
        Glide.with(holder.itemView.context).load(Constant.HOST_IMAGE+item.icon).into(holder.binding.imgIcon)

        holder.binding.categoryName.text = item.title

        if (item.checked){
            holder.binding.categoryCard.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
            holder.binding.categoryName.setTextColor(Color.WHITE)
        }else{
            holder.binding.categoryCard.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.grey))
            holder.binding.categoryName.setTextColor(Color.BLACK)
        }
    }
}









