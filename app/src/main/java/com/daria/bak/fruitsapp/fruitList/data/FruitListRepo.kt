package com.daria.bak.fruitsapp.fruitList.data

import android.util.Log
import com.daria.bak.fruitsapp.fruitList.business.Fruit

interface FruitListRepoInterface {
    var onFruitDownloadedListener: ((fruitList: ArrayList<Fruit>) -> Unit)?
    fun getFruitList(handler: (ArrayList<Fruit>) -> Unit)
}
class FruitListRepo(private val service: FruitListServiceInterface): FruitListRepoInterface {
    override var onFruitDownloadedListener: ((fruitList: ArrayList<Fruit>) -> Unit)? = null



    override fun getFruitList(handler: (ArrayList<Fruit>) -> Unit) {

        service.getFruitList() { fruitListDTO ->
            var fruitList = fruitListDTO.map { fruitDTO -> Fruit(fruitDTO)} as ArrayList<Fruit>
            Log.i("FruitListRepo", "getFruitList repo invoked")
            handler.invoke(fruitList)
        }
    }
}