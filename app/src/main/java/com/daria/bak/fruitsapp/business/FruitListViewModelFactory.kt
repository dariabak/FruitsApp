package com.daria.bak.fruitsapp.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daria.bak.fruitsapp.data.FruitListRepoInterface

class FruitListViewModelFactory(private val repo: FruitListRepoInterface): ViewModelProvider.NewInstanceFactory()  {

    override fun <T: ViewModel?> create(modelClass:Class<T>): T {
        return FruitListViewModel(repo) as T
    }
}