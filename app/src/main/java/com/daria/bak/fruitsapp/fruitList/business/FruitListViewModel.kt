package com.daria.bak.fruitsapp.fruitList.business

import android.util.Log

import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.fruitList.data.FruitListRepoInterface


class FruitListViewModel(private val repo: FruitListRepoInterface): ViewModel() {
    var onFruitDownloadedListener: ((fruitList: ArrayList<Fruit>) -> Unit)? = null


    var fruitList: ArrayList<Fruit> = ArrayList<Fruit>()

    init {
        repo.getFruitList() { list ->
            saveList(list)
        }
    }
    fun getFruitList() {
        repo.getFruitList() { list ->
            Log.i("FruitListViewModel", "getFruitList viewModel invoked")
            saveList(list)
        }

    }
    private fun saveList(list: ArrayList<Fruit>) {

        fruitList = list
        onFruitDownloadedListener?.invoke(list)
        Log.i("FruitListViewModel", "Yeey")
    }

    fun refreshData() {
        Log.i("FruitListViewModel", "Data refreshed")
    }

}