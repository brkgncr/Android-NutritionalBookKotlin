package com.burak.nutritionalvaluebook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.burak.nutritionalvaluebook.model.Nutrition
import com.burak.nutritionalvaluebook.roomdb.NutritionDatabase
import com.burak.nutritionalvaluebook.service.NutritionAPIService
import com.burak.nutritionalvaluebook.util.SpecialSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NutritionListViewModel(application: Application) : AndroidViewModel(application) {

    val nutritions = MutableLiveData<List<Nutrition>>()
    val nutritionErrorMessage = MutableLiveData<Boolean>()
    val nutritionLoading = MutableLiveData<Boolean>()


    private val nutritionApiService = NutritionAPIService()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    private val updateTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val saveTime = specialSharedPreferences.timeCapture()

        if(saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime) {
            dataCollectRoom()
        } else  {
            dataCollectInternet()
        }
    }

    private fun dataCollectRoom() {
        nutritionLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutritionList = NutritionDatabase(getApplication()).nutritionDao().getAllNutrition()
            withContext(Dispatchers.Main) {
                showNutrition(nutritionList)
                Toast.makeText(getApplication(),"Nutrition get Room", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun refreshFromInternet() {
        dataCollectInternet()
    }

    private fun dataCollectInternet() {
        nutritionLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val nutritionList = nutritionApiService.getData()
            withContext(Dispatchers.Main) {
                nutritionLoading.value = false
                saveRoom(nutritionList)
                Toast.makeText(getApplication(),"Nutritions Collect Internet",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showNutrition(nutritionList: List<Nutrition>) {
        nutritions.value = nutritionList
        nutritionErrorMessage.value = false
        nutritionLoading.value = false
    }

    private fun saveRoom(nutritionList : List<Nutrition>) {

        viewModelScope.launch {

            val dao = NutritionDatabase(getApplication()).nutritionDao()
            dao.deleteAllNutrition()
            val uuidList =dao.insertAll(*nutritionList.toTypedArray())
            var i = 0
            while (i < nutritionList.size) {
                nutritionList[i].uuid = uuidList[i].toInt()
                i += 1
            }

            showNutrition(nutritionList)
        }

        specialSharedPreferences.timeSave(System.nanoTime())
    }

}