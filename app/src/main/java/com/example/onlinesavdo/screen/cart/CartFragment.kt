package com.example.onlinesavdo.screen.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinesavdo.databinding.FragmentCartBinding
import com.example.onlinesavdo.screen.MainViewModel
import com.example.onlinesavdo.screen.makeorder.MakeOrderActivity
import com.example.onlinesavdo.utils.PrefUtils
import com.example.onlinesavdo.view.CartAdapter
import java.io.Serializable

class CartFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.errorData.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.productData.observe(this, Observer {
            binding.recycler.adapter = CartAdapter(it)

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager = LinearLayoutManager(requireActivity())

        binding.swipe.setOnRefreshListener {
            loadData()
        }
        binding.btnMakeOrder.setOnClickListener {
            val intent = Intent(requireActivity(), MakeOrderActivity::class.java)
//            intent.putExtra(Constants.EXTRA_DATA, (viewModel.productsData.value ?: emptyList<ProductModel>()) as Serializable)
            startActivity(intent)
        }


        loadData()
    }
    fun loadData() {
        viewModel.getTopProductByIds(PrefUtils.getCartList().map { it.product_id})
    }

    companion object {

        @JvmStatic
        fun newInstance() = CartFragment()

    }
}