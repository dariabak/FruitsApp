package com.daria.bak.fruitsapp.fruitList.business

import com.daria.bak.fruitsapp.fruitList.data.FruitDTO

class Fruit {
    constructor(fruitDTO: FruitDTO){
        this.type = fruitDTO.type
        this.price = fruitDTO.price
        this.weight = fruitDTO.weight
    }
    var type: String
    var price: Float
    var weight: Float
}