package com.daria.bak.fruitsapp.fruitList.ui

import com.daria.bak.fruitsapp.fruitList.business.Fruit

sealed class FruitListState {
    data class Success(val fruitList: ArrayList<Fruit>) : FruitListState()
    data class Error(val message: String) : FruitListState()
    object Loading : FruitListState()
}