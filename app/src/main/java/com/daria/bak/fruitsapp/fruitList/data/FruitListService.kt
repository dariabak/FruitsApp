package com.daria.bak.fruitsapp.fruitList.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.Delegates

interface FruitListServiceInterface {
    fun getFruitList(handler: (ArrayList<FruitDTO>) -> Unit)
}
class FruitListService(private var queue: RequestQueue): FruitListServiceInterface {
private var requestStartTime: Long = 0
    override fun getFruitList(handler: (ArrayList<FruitDTO>) -> Unit){
        requestStartTime = System.currentTimeMillis()
        var gson = Gson()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                var requestTime = System.currentTimeMillis() - requestStartTime
                Log.i("FruitListService", "$requestTime")
                successfulRequest(requestTime)
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

    fun successfulRequest(data: Long) {
        var url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=load&data=$data"
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String>
            { response ->
                var strResp = response.toString()
                Log.d("FruitListService", strResp)
            },
            Response.ErrorListener {Log.d("FruitListService", "that didn't work") })
        queue.add(stringReq)
    }

    fun viewLoaded(data: Long) {
        var url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=display&data=$data"
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String>
            { response ->
                var strResp = response.toString()
                Log.d("FruitListService", strResp)
            },
            Response.ErrorListener {Log.d("FruitListService", "that didn't work") })
        queue.add(stringReq)
    }
}