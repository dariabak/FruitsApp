package com.daria.bak.fruitsapp.business

import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.data.FruitListRepoInterface

class FruitListViewModel(private val repo: FruitListRepoInterface): ViewModel() {
    fun getFruitList(): ArrayList<Fruit> {
        return repo.getFruitList()
    }
}