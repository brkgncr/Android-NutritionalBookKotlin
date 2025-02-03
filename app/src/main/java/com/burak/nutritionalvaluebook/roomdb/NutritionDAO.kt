package com.burak.nutritionalvaluebook.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.burak.nutritionalvaluebook.model.Nutrition

@Dao
interface NutritionDAO {

    @Insert
    suspend fun insertAll(vararg nutrition: Nutrition)

    @Query("SELECT * FROM nutrition")
    suspend fun getAllNutrition() : List<Nutrition>

    @Query("DELETE FROM nutrition")
    suspend fun deleteAllNutrition()

    @Query("SELECT * FROM nutrition WHERE uuid = :nutritionID")
    suspend fun getNutrition(nutritionID : Int) : Nutrition
}