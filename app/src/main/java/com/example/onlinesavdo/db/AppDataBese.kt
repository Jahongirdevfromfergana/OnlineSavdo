package com.example.onlinesavdo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlinesavdo.model.CategoryModel
import com.example.onlinesavdo.model.ProductModel


@Database(entities = [CategoryModel::class, ProductModel::class], version = 1)

abstract class AppDataBese: RoomDatabase() {
    abstract fun getProductsDao(): ProductDao
    abstract fun getCategoriesDao(): CategoryDao
    companion object{
         var INSTANCE: AppDataBese? = null

        fun initDataBase(context: Context){
            if (INSTANCE == null){
                synchronized(AppDataBese::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDataBese::class.java, "online_shop_dp").build()

                }
            }

        }
        fun getDataBase(): AppDataBese{
            return INSTANCE!!
        }
    }
}