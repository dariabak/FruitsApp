package com.daria.bak.fruitsapp.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.data.FruitListRepoInterface


class FruitListViewModel(private val repo: FruitListRepoInterface): ViewModel() {
    var onFruitDownloadedListener: ((fruitList: ArrayList<Fruit>) -> Unit)? = null
    private val _fruitListLive = MutableLiveData<ArrayList<Fruit>>()
    val fruitListLive: LiveData<ArrayList<Fruit>>
    get() = _fruitListLive

    var fruitList: ArrayList<Fruit> = ArrayList<Fruit>()

    init {
        repo.getFruitList() { list ->

            saveList(list)
        }
    }
    fun getFruitList() {
        repo.getFruitList() { list ->
//            onFruitDownloadedListener?.invoke(list)
            Log.i("FruitListViewModel", "getFruitList viewModel invoked")
            saveList(list)
        }
//    repo.onFruitDownloadedListener = { list ->
//        _fruitList.value = list
//        }
    }
    private fun saveList(list: ArrayList<Fruit>) {
        _fruitListLive.value = list
        fruitList = list
        onFruitDownloadedListener?.invoke(list)
        Log.i("FruitListViewModel", "Yeey")
    }

}