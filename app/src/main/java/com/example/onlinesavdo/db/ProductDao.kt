package com.example.onlinesavdo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlinesavdo.model.ProductModel


@Dao
interface ProductDao : List<ProductModel> {

    @Query("DELETE from products")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ProductModel>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductModel>
}