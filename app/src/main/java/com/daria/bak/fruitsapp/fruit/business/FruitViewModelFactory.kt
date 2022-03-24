package com.daria.bak.fruitsapp.fruit.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daria.bak.fruitsapp.fruit.data.FruitClientInterface



class FruitViewModelFactory(private val client: FruitClientInterface): ViewModelProvider.NewInstanceFactory()  {

    override fun <T: ViewModel?> create(modelClass:Class<T>): T {
        return FruitViewModel(client) as T
    }
}