package com.example.onlinesavdo.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlinesavdo.api.NetworkManager
import com.example.onlinesavdo.model.*
import com.example.onlinesavdo.model.getproductsrequest.GetProductByIdsRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Repository {
    val compositeDisposable = CompositeDisposable()
    fun getOffers(
        progress: MutableLiveData<Boolean>,
        errorData: MutableLiveData<String>,
        offersData: MutableLiveData<List<OfferModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponseModel<List<OfferModel>>>() {
                    override fun onNext(t: BaseResponseModel<List<OfferModel>>) {
                        if (t.success) {
                            progress.value = false
                            offersData.value = t.data
                        }
                    }
                    override fun onError(e: Throwable) {
                        progress.value = false
                        errorData.value = e.localizedMessage
                    }
                    override fun onComplete() {
                    }
                })
        )
    }

    fun getCategories(
        errorData: MutableLiveData<String>,
        categoryData: MutableLiveData<List<CategoryModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableObserver<BaseResponseModel<List<CategoryModel>>>() {
                    override fun onNext(t: BaseResponseModel<List<CategoryModel>>) {
                        if (t.success) {
                            categoryData.value = t.data
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorData.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }

    fun getTopProducts(
        errorData: MutableLiveData<String>,
        productData: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(NetworkManager.getApiService().getTopProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BaseResponseModel<List<ProductModel>>>() {
                override fun onNext(t: BaseResponseModel<List<ProductModel>>) {
                    if (t.success) {
                        productData.value = t.data
                    } else {
                        errorData.value = t.message
                    }
                }

                override fun onError(e: Throwable) {
                    errorData.value = e.localizedMessage
                }

                override fun onComplete() {
                }
            })
        )
    }
    fun getTopProductsByCategory(
        id: Int,
        errorData: MutableLiveData<String>,
        productData: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategoryById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableObserver<BaseResponseModel<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponseModel<List<ProductModel>>) {
                        if (t.success) {
                            productData.value = t.data
                        } else {
                            errorData.value = t.message
                        }
                    }
                    override fun onError(e: Throwable) {
                        errorData.value = e.localizedMessage
                    }
                    override fun onComplete() {
                    }
                })
        )
    }

    fun getTopProductsByIds(
        ids: List<Int>,
        progress: MutableLiveData<Boolean>,
        errorData: MutableLiveData<String>,
        productData: MutableLiveData<List<ProductModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getProductByIds(GetProductByIdsRequest(ids))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableObserver<BaseResponseModel<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponseModel<List<ProductModel>>) {
                        if (t.success) {
                            progress.value = false
                            productData.value = t.data
                        } else {
                            errorData.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        errorData.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }


}

