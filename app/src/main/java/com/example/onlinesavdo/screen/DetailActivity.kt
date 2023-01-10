package com.example.onlinesavdo.screen

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.databinding.ActivityDetailBinding
import com.example.onlinesavdo.model.ProductModel
import com.example.onlinesavdo.utils.Constant
import com.example.onlinesavdo.utils.PrefUtils

class DetailActivity : AppCompatActivity() {
    lateinit var item: ProductModel
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        item = intent.getSerializableExtra(Constant.EXTRA_DATA) as ProductModel

        binding.btnFavorite.setOnClickListener {
            PrefUtils.setFavorite(item)
            if (PrefUtils.checkFavorite(item)) {
                Toast.makeText(this, "Add to Favorites", Toast.LENGTH_LONG).show()
                binding.imgFavorite.setImageResource(R.drawable.heart2)
            } else {
                Toast.makeText(this, "Remove to Favorites", Toast.LENGTH_LONG).show()

                binding.imgFavorite.setImageResource(R.drawable.ic_heart)
            }
        }



        Glide.with(this).load(Constant.HOST_IMAGE + item.image).into(binding.productImg)
        binding.productNames.text = item.name
        binding.tvTitle.text = item.name
        binding.tvPrice.text = item.price

        if (PrefUtils.checkFavorite(item)) {
            binding.imgFavorite.setImageResource(R.drawable.heart2)
        } else {
            binding.imgFavorite.setImageResource(R.drawable.ic_heart)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        if (PrefUtils.getCartCount(item)>0){
            binding.addToCart.visibility = View.GONE
        }
        binding.addToCart.setOnClickListener {
            item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Add to cart!", Toast.LENGTH_LONG).show()
            finish()

        }

    }
}