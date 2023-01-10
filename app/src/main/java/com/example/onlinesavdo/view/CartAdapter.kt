package com.example.onlinesavdo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinesavdo.databinding.CartItemLayoutBinding
import com.example.onlinesavdo.model.CartModel
import com.example.onlinesavdo.model.ProductModel
import com.example.onlinesavdo.utils.Constant

class CartAdapter(val items: List<ProductModel >): RecyclerView.Adapter<CartAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: CartItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.tvPrice.text = item.price
        holder.binding.tvName.text = item.name
        Glide.with(holder.itemView.context).load(Constant.HOST_IMAGE+item.image).into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int {
       return items.count()
    }
}