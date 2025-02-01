package com.burak.nutritionalvaluebook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Nutrition(
    @ColumnInfo(name = "name")
    @SerializedName("isim")
    val nutritionName : String?,
    @ColumnInfo(name = "colorie")
    @SerializedName("kalori")
    val nutritionCalorie : String?,
    @ColumnInfo(name = "carbohydrate")
    @SerializedName("karbonhidrat")
    val nutritionCarbohydrate : String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val nutritionProtein : String?,
    @ColumnInfo(name = "fat")
    @SerializedName("yag")
    val nutritionFat : String?,
    @ColumnInfo(name = "image")
    @SerializedName("gorsel")
    val nutritionImage : String?

) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}