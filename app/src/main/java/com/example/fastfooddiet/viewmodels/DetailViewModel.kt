package com.example.fastfooddiet.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.fastfooddiet.data.*
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val foodRepo : FoodRepo
    private val nutritionRepo : NutritionRepo
    private val mealRepo : MealRepo

    init {
        AppDatabase.getDatabase(application).apply {
            foodRepo = FoodRepo(foodDao())
            nutritionRepo = NutritionRepo(nutritionDao())
            mealRepo = MealRepo(mealDao())
        }
    }

    //***************

    val foodId = MutableLiveData<Int>()

    fun setFood(id : Int) {
        foodId.value = id
    }

    val food : LiveData<Food> = foodId.switchMap {id ->
        foodRepo.getFood(id)
    }

    val foodSizes = MutableLiveData<List<FoodSize>>()
    fun getFoodSizes(name: String, restaurant: String) = viewModelScope.launch {
        val sizes = foodRepo.getFoodSizes(name, restaurant)
        foodSizes.value = sizes
    }

    //***************
    val mealDatas : LiveData<List<MealData>> = mealRepo.getMealDatas()

    fun addMealFood(mealId: Int, foodId: Int) = viewModelScope.launch {
        mealRepo.insertMealFood(MealFoodCrossRef(mealId = mealId, foodId = foodId))
    }

    fun addMealAndFood(name : String, foodId: Int) = viewModelScope.launch {
        val mealId = mealRepo.insertMeal(MealData(name = name)).toInt()
        mealRepo.insertMealFood(MealFoodCrossRef(mealId = mealId, foodId = foodId))
    }

    val isCustomNutritionData = MutableLiveData<Boolean>(false)

    val nutrition : LiveData<Nutrition> = isCustomNutritionData.switchMap { isCustomData ->
        if (isCustomData)
            nutritionRepo.getCustomNutrition()
        else
            nutritionRepo.getMasterNutrition()
    }

    fun setFavorite(id: Int, isFavorite : Boolean) {
        viewModelScope.launch {
            foodRepo.setFavorite(id, !isFavorite)
        }
    }
}