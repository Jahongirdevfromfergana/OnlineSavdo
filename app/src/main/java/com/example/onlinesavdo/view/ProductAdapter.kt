package com.example.onlinesavdo.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinesavdo.databinding.ProductItemLayoutBinding
import com.example.onlinesavdo.model.ProductModel
import com.example.onlinesavdo.screen.DetailActivity
import com.example.onlinesavdo.utils.Constant

class ProductAdapter(val items: List<ProductModel>): RecyclerView.Adapter<ProductAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: ProductItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(Constant.EXTRA_DATA, item)
            it.context.startActivity(intent)
        }
        holder.binding.productPrice.text = item.price
        holder.binding.textTitle.text = item.name
        Glide.with(holder.itemView.context).load(Constant.HOST_IMAGE + item.image).into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int = items.count()
}





