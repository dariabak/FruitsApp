package com.daria.bak.fruitsapp.data

import android.content.Context

interface FruitListServiceInterface {
    fun getFruitList(): ArrayList<FruitDTO>
}
class FruitListService(val context: Context): FruitListServiceInterface {
    override fun getFruitList(): ArrayList<FruitDTO> {

    }
}