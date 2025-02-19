package com.burak.nutritionalvaluebook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.burak.nutritionalvaluebook.model.Nutrition
import com.burak.nutritionalvaluebook.roomdb.NutritionDatabase
import kotlinx.coroutines.launch

class NutritionDetailViewModel(application: Application) : AndroidViewModel(application) {

    val nutritionLiveDava = MutableLiveData<Nutrition>()

    fun roomDataUse(uuid : Int) {
        viewModelScope.launch {
            val dao = NutritionDatabase(getApplication()).nutritionDao()
            val nutrition = dao.getNutrition(uuid)
            nutritionLiveDava.value = nutrition
        }
    }
}