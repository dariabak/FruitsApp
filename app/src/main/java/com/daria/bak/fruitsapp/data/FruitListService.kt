package com.daria.bak.fruitsapp.data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.daria.bak.fruitsapp.business.Fruit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface FruitListServiceInterface {
    var onFruitDownloadedListener: ((fruitDtoList: ArrayList<FruitDTO>) -> Unit)?
    fun getFruitList(handler: (ArrayList<FruitDTO>) -> Unit)
}
class FruitListService(private var queue: RequestQueue): FruitListServiceInterface {
    override var onFruitDownloadedListener: ((fruitDtoList: ArrayList<FruitDTO>) -> Unit)? = null

    override fun getFruitList(handler: (ArrayList<FruitDTO>) -> Unit){
        var gson = Gson()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val json = response.getJSONArray("fruit").toString()
                val string: String = response.toString()
                val fruitDtoType = object : TypeToken<ArrayList<FruitDTO>>() {}.type
                var fruitDtoList: ArrayList<FruitDTO> = gson.fromJson(json, fruitDtoType)
                Log.i("FruitListService", "Invoked")
                handler.invoke(fruitDtoList)

            }, { _ ->
                Log.e("FruitListService", "Error")
            }
        )
        queue.add(jsonObjectRequest)
    }
}