package com.example.onlinesavdo.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlinesavdo.R
import com.example.onlinesavdo.databinding.FragmentHomeBinding
import com.example.onlinesavdo.model.CategoryModel
import com.example.onlinesavdo.model.OfferModel
import com.example.onlinesavdo.screen.MainViewModel
import com.example.onlinesavdo.utils.Constant
import com.example.onlinesavdo.view.CategoryAdapter
import com.example.onlinesavdo.view.CategoryAdapterCallback
import com.example.onlinesavdo.view.ProductAdapter


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    var offers: List<OfferModel> = emptyList()
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipe.setOnRefreshListener{
            loadData()
        }
        binding.recyclerCategories.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerProducts.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.errorData.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.progress.observe(requireActivity(), Observer {
            binding.swipe.isRefreshing = it
        })
        viewModel.offersData.observe(requireActivity(), Observer {
            binding.carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load("http://osonsavdo.devapp.uz/images/"+it[position].image).into(imageView)
            }
           binding.carouselView.pageCount = it.count()
        })
        viewModel.categoryData.observe(requireActivity(), Observer {
            binding.recyclerCategories.adapter = CategoryAdapter(it, object: CategoryAdapterCallback {
                override fun onClick(item: CategoryModel) {
                    viewModel.getTopProductByCategory(item.id)
                }
            })
        })
        viewModel.productData.observe(requireActivity(), Observer {
            binding.recyclerProducts.adapter = ProductAdapter(it)
        })
        loadData()
    }
    fun loadData() {
        viewModel.getOffers()
        viewModel.getCategories()
        viewModel.getTopProducts()
//         viewModel.getTopProducts()
    }
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}