package com.burak.nutritionalvaluebook.model

import com.google.gson.annotations.SerializedName

data class Nutrition(
    @SerializedName("isim")
    val nutritionName : String?,
    @SerializedName("kalori")
    val nutritionCalorie : String?,
    @SerializedName("karbonhidrat")
    val nutritionCarbohydrate : String?,
    @SerializedName("protein")
    val nutritionProtein : String?,
    @SerializedName("yag")
    val nutritionYFat : String?,
    @SerializedName("gorsel")
    val nutritionImage : String?


)