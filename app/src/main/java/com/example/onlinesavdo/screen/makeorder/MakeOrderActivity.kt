package com.example.onlinesavdo.screen.makeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinesavdo.databinding.ActivityMakeOrderBinding
import com.example.onlinesavdo.model.AddressModel
import com.example.onlinesavdo.model.ProductModel
import com.example.onlinesavdo.screen.MapsActivity
import com.example.onlinesavdo.utils.Constant
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityMakeOrderBinding
    var address: AddressModel?=null
    lateinit var items: List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMakeOrderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        items = intent.getSerializableExtra(Constant.EXTRA_DATA) as List<ProductModel>

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }
        binding.tvTotalAmount.setText(items.sumByDouble { it.cartCount.toDouble()*(it.price.replace("", " ").toDoubleOrNull() ?: 0.0 )}.toString())

        binding.addAddress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }
    @Subscribe
   fun onEvent(address: AddressModel){
       this.address = address
        binding.addAddress.setText("${address.longitude},  ${address.longitude}")

    }
}