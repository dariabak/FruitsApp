package com.daria.bak.fruitsapp

import com.daria.bak.fruitsapp.fruitList.business.Fruit
import com.daria.bak.fruitsapp.fruitList.data.FruitListClientInterface

class MockFruitListClient: FruitListClientInterface {
    var getFruitListReturnValue: Result<ArrayList<Fruit>> = Result.success(arrayListOf());
    var getFruitListCalled = false

    override fun getFruitList(handler: (Result<ArrayList<Fruit>>) -> Unit) {
        getFruitListCalled = true
        handler(getFruitListReturnValue)
    }

    override fun sendViewLoadingAnalytics(data: Long) {

    }

    override fun sendErrorAnalytics() {

    }
}