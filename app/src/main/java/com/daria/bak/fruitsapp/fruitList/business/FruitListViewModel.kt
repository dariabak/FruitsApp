package com.daria.bak.fruitsapp.fruitList.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.fruitList.data.FruitListRepoInterface
import com.daria.bak.fruitsapp.fruitList.ui.FruitListState
import java.lang.Exception


class FruitListViewModel(private val repo: FruitListRepoInterface): ViewModel() {
    var onFruitDownloadedListener: ((fruitList: ArrayList<Fruit>) -> Unit)? = null
    private var _fruitListLiveData = MutableLiveData<ArrayList<Fruit>>()
    val fruitListLiveData: LiveData<ArrayList<Fruit>>
        get() = _fruitListLiveData
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
//            _fruitListState.value = FruitListState.Success(fruitList)
            getFruitList()
        } catch(e: Exception) {
            _fruitListState.value = FruitListState.Error(defaultError)
        }
    }
    fun getFruitList() {
        repo.getFruitList() { list ->
            Log.i("FruitListViewModel", "getFruitList viewModel invoked")
            fruitList = list
            _fruitListLiveData.value = list
            _fruitListState.value = FruitListState.Success(list)
//            onFruitDownloadedListener?.invoke(list)
        }

    }
    private fun saveList(list: ArrayList<Fruit>) {
        fruitList = list
        _fruitListState.value = FruitListState.Success(fruitList)
        onFruitDownloadedListener?.invoke(list)
        Log.i("FruitListViewModel", "Yeey")
    }

    fun refreshData() {
        _fruitListState.value =  FruitListState.Loading
        getFruitList()
        Log.i("FruitListViewModel", "Data refreshed")
    }

}