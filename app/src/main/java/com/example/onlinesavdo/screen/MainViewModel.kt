package com.example.onlinesavdo.screen

import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinesavdo.api.Api
import com.example.onlinesavdo.api.NetworkManager
import com.example.onlinesavdo.api.repository.Repository
import com.example.onlinesavdo.model.BaseResponseModel
import com.example.onlinesavdo.model.CategoryModel
import com.example.onlinesavdo.model.OfferModel
import com.example.onlinesavdo.model.ProductModel
import com.example.onlinesavdo.utils.Constant
import com.example.onlinesavdo.view.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel: ViewModel() {
    val repository = Repository()

    var errorData = MutableLiveData<String>()
    var offersData = MutableLiveData<List<OfferModel>>()
    var categoryData = MutableLiveData<List<CategoryModel>>()
    var productData = MutableLiveData<List<ProductModel>>()
    val progress = MutableLiveData<Boolean>()

    fun getOffers() {
        repository.getOffers(progress, errorData, offersData)
    }
    fun getCategories(){
        repository.getCategories(errorData, categoryData)
    }
    fun getTopProducts(){
        repository.getTopProducts(errorData, productData)
    }
    fun getTopProductByCategory(id: Int){
        repository.getTopProductsByCategory(id, errorData, productData)
    }
     fun getTopProductByIds(ids: List<Int>){
         repository.getTopProductsByIds(ids, progress, errorData, productData)
     }
}










