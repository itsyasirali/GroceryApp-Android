package com.itsyasirali.groceriesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itsyasirali.groceriesapp.data.local.dao.ProductDao
import com.itsyasirali.groceriesapp.domain.model.ProductItem

@Database(entities = [ProductItem::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}