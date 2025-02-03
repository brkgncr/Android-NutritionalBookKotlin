package com.burak.nutritionalvaluebook.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.burak.nutritionalvaluebook.model.Nutrition

@Database(entities = [Nutrition::class], version = 1)
abstract class NutritionDatabase : RoomDatabase() {
    abstract fun nutritionDao() : NutritionDAO
}
