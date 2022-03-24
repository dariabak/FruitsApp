package com.daria.bak.fruitsapp.fruitList.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.fruitList.data.FruitListRepoInterface
import com.daria.bak.fruitsapp.fruitList.ui.FruitListState
import java.lang.Exception


class FruitListViewModel(private val repo: FruitListRepoInterface): ViewModel() {
    var fruitList: ArrayList<Fruit> = ArrayList<Fruit>()
    private var _fruitListState = MutableLiveData<FruitListState>()
    val fruitListState: LiveData<FruitListState>
        get() = _fruitListState
    private val defaultError = "Something went wrong. Please try again later."

    init {
        _fruitListState.value =  FruitListState.Loading
        try {
          getFruitList()
        } catch(e: Exception) {
            _fruitListState.value = FruitListState.Error(defaultError)
        }

    }

    fun fetchData() {
        if(_fruitListState.value is FruitListState.Success) return
        _fruitListState.value =  FruitListState.Loading

        try{
            getFruitList()
        } catch(e: Exception) {
            _fruitListState.value = FruitListState.Error(defaultError)
        }
    }
    fun getFruitList() {
        repo.getFruitList() { list ->
            Log.i("FruitListViewModel", "getFruitList viewModel invoked")
            fruitList = list
            _fruitListState.value = FruitListState.Success(list)
        }

    }
    private fun saveList(list: ArrayList<Fruit>) {
        fruitList = list
        _fruitListState.value = FruitListState.Success(fruitList)
        Log.i("FruitListViewModel", "Yeey")
    }

    fun refreshData() {
        _fruitListState.value =  FruitListState.Loading
        getFruitList()
        Log.i("FruitListViewModel", "Data refreshed")
    }

}