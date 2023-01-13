package com.example.onlinesavdo.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlinesavdo.api.repository.Repository
import com.example.onlinesavdo.db.AppDataBese
import com.example.onlinesavdo.model.CategoryModel
import com.example.onlinesavdo.model.OfferModel
import com.example.onlinesavdo.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
    fun insertAllProducts2DB(items: List<ProductModel>){
        CoroutineScope(Dispatchers.IO).launch{
//            AppDataBese.getDataBase().productDao().deleteAll()
            AppDataBese.getDataBase().getProductsDao().insertAll(items)
        }
    }

    fun insertAllCategories2DB(items: List<CategoryModel>){
        CoroutineScope(Dispatchers.IO).launch{
//            AppDataBese.getDataBase().categoryDao().deleteAll()
            AppDataBese.getDataBase().getCategoriesDao().insertAll(items)
        }
    }

    fun getAllDBProducts(){
        CoroutineScope(Dispatchers.Main).launch{
            productData.value = withContext(Dispatchers.IO){AppDataBese.getDataBase().getProductsDao().getAllProducts()}
        }

    }

    fun getAllDBCategories(){
        CoroutineScope(Dispatchers.Main).launch{
            categoryData.value = withContext(Dispatchers.IO){AppDataBese.getDataBase().getCategoriesDao().getAllCategories()}
        }

    }


}










