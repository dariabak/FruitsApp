package com.daria.bak.fruitsapp.fruitList.data

import android.util.Log
import com.daria.bak.fruitsapp.fruitList.business.Fruit

interface FruitListRepoInterface {
    fun getFruitList(handler: (ArrayList<Fruit>) -> Unit)
}
class FruitListRepo(private val service: FruitListServiceInterface): FruitListRepoInterface {

    override fun getFruitList(handler: (ArrayList<Fruit>) -> Unit) {

        service.getFruitList() { fruitListDTO ->
            var fruitList = fruitListDTO.map { fruitDTO -> Fruit(fruitDTO)} as ArrayList<Fruit>
            Log.i("FruitListRepo", "getFruitList repo invoked")
            handler.invoke(fruitList)
        }
    }
}