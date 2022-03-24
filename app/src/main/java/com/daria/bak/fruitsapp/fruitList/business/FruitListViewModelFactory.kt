package com.daria.bak.fruitsapp.fruitList.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daria.bak.fruitsapp.fruitList.data.FruitListClientInterface


class FruitListViewModelFactory(private val client: FruitListClientInterface): ViewModelProvider.NewInstanceFactory()  {

    override fun <T: ViewModel?> create(modelClass:Class<T>): T {
        return FruitListViewModel(client) as T
    }
}