package com.example.onlinesavdo.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinesavdo.databinding.FragmentFavoriteBinding
import com.example.onlinesavdo.screen.MainViewModel
import com.example.onlinesavdo.utils.PrefUtils
import com.example.onlinesavdo.view.ProductAdapter

class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.productData.observe(this, Observer {
            binding.recyclerProduct.adapter = ProductAdapter(it)
        })

        viewModel.errorData.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerProduct.layoutManager = LinearLayoutManager(requireContext())

       binding.swipe.setOnRefreshListener {
           loadData()
       }
        loadData()
    }
    fun loadData() {
        viewModel.getTopProductByIds(PrefUtils.getFavoriteList())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}