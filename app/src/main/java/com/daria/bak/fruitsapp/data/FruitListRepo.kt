package com.daria.bak.fruitsapp.data

import com.daria.bak.fruitsapp.business.Fruit

interface FruitListRepoInterface {
    fun getFruitList(): ArrayList<Fruit>
}
class FruitListRepo(private val service: FruitListServiceInterface): FruitListRepoInterface {
    override fun getFruitList(): ArrayList<Fruit> {
        var fruitList: ArrayList<Fruit> = ArrayList<Fruit>()
        var fruitListDTO: ArrayList<FruitDTO> = service.getFruitList()
        return fruitList
    }
}