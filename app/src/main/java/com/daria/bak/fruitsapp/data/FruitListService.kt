package com.daria.bak.fruitsapp.data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface FruitListServiceInterface {
    fun getFruitList(handler: (FruitDTO) -> Unit)
}
class FruitListService(private var queue: RequestQueue): FruitListServiceInterface {
    override fun getFruitList(handler: (FruitDTO) -> kotlin.Unit){
        var gson = Gson()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val string: String = response.toString()
                val fruitDtoType = object : TypeToken<FruitDTO>() {}.type
                var fruitDTO: FruitDTO = gson.fromJson(string, fruitDtoType)
                handler.invoke(fruitDTO)

            }, { _ ->
                Log.e("WeatherService", "Error")
            }
        )
        queue.add(jsonObjectRequest)
    }
}