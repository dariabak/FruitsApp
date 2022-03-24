package com.daria.bak.fruitsapp.fruitList.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.fruitList.data.FruitListClientInterface
import com.daria.bak.fruitsapp.fruitList.ui.FruitListState
import java.lang.Exception


class FruitListViewModel(private val client: FruitListClientInterface): ViewModel() {
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

    fun getFruitList() {
        client.getFruitList() { result ->
            Log.i("FruitListViewModel", "getFruitList viewModel invoked")
            if(result.isSuccess) {
                fruitList = result.getOrDefault(fruitList)
                _fruitListState.value = FruitListState.Success(result.getOrDefault(fruitList))
            } else if (result.isFailure) {
                _fruitListState.value = FruitListState.Error(defaultError)
                sendErrorAnalytics()
            }
        }

    }

    fun refreshData() {
        _fruitListState.value =  FruitListState.Loading
        getFruitList()
        Log.i("FruitListViewModel", "Data refreshed")
    }
    fun sendViewLoadingAnalytics(time: Long) {
        client.sendViewLoadingAnalytics(time)
    }

    private fun sendErrorAnalytics() {
        client.sendErrorAnalytics()
    }
}