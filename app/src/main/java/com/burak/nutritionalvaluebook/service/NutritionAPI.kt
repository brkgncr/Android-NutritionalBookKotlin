package com.burak.nutritionalvaluebook.service

import com.burak.nutritionalvaluebook.model.Nutrition
import retrofit2.http.GET

interface NutritionAPI {

    //

    //BASE URL -> https://raw.githubusercontent.com/
    //ENDPOINT -> atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getNutrition() : List<Nutrition>
}