package com.example.onlinesavdo.utils

import com.example.onlinesavdo.model.CartModel
import com.example.onlinesavdo.model.ProductModel
import com.orhanobut.hawk.Hawk


object PrefUtils {
    const val PREF_FAVORITES = "pref_favorites"
    const val PREF_CART = "pref_cart"

    fun setFavorite(item: ProductModel){
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        if (items.filter { it == item.id }.firstOrNull() != null){
            items.remove(item.id)
        }else{
            items.add(item.id)
        }
        Hawk.put(PREF_FAVORITES, items)
    }
    fun getFavoriteList(): ArrayList<Int>{
        return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    }
    fun checkFavorite(item: ProductModel): Boolean{
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        return items.filter { it == item.id }.firstOrNull() != null
    }
    fun checkCart(item: CartModel): Boolean{
        val items = Hawk.get(PREF_CART, arrayListOf<Int>())
        return items.filter { it == item.count }.firstOrNull() != null
    }
    fun setCart(item: ProductModel){
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        val cart = items.filter { it.product_id == item.id }.firstOrNull()
        if (cart != null){
            if (item.cartCount > 0){
                cart.count = item.cartCount
            }else{
                items.remove(cart)
            }
        }else{
            val newCart = CartModel(item.id, item.cartCount)
            items.add(newCart)
        }
        Hawk.put(PREF_CART, items)
    }
    fun getCartList(): ArrayList<CartModel>{
        return Hawk.get(PREF_CART, arrayListOf<CartModel>())
    }
    fun getCartCount(item: ProductModel): Int{
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        return items.filter { it.product_id == item.id }.firstOrNull()?.count ?: 0
    }
//    fun setToken(value: String){
//        Hawk.put(PREF_TOKEN, value)
//    }
//
//    fun getToken(): String{
//        return Hawk.get(PREF_TOKEN, "")
//    }
//
//    fun setFCMToken(value: String){
//        Hawk.put(PREF_FCM_TOKEN, value)
//    }
//
//    fun getFCMToken(): String{
//        return Hawk.get(PREF_FCM_TOKEN, "")
//    }
}