package com.example.onlinesavdo.api

import com.example.onlinesavdo.model.*
import com.example.onlinesavdo.model.getproductsrequest.GetProductByIdsRequest
import io.reactivex.Observable
import retrofit2.http.*

interface Api {
    @GET("get_offers")
    fun getOffers():  Observable<BaseResponseModel<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResponseModel<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): Observable<BaseResponseModel<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryById(@Path("category_id") categoryId: Int): Observable<BaseResponseModel<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductByIds(@Body request: GetProductByIdsRequest): Observable<BaseResponseModel<List<ProductModel>>>
}