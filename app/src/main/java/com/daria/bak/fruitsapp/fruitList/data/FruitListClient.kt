package com.daria.bak.fruitsapp.fruitList.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.daria.bak.fruitsapp.fruitList.business.Fruit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface FruitListClientInterface {
    fun getFruitList(handler: (ArrayList<Fruit>) -> Unit)
    fun viewLoaded(data: Long)
}
class FruitListClient(private var queue: RequestQueue): FruitListClientInterface {
    private var requestStartTime: Long = 0

    override fun getFruitList(handler: (ArrayList<Fruit>) -> Unit) {

        getData() { fruitListDTO ->
            var fruitList = fruitListDTO.map { fruitDTO -> Fruit(fruitDTO)} as ArrayList<Fruit>
            Log.i("FruitListClient", "getFruitListClient invoked")
            handler.invoke(fruitList)
        }
    }

    private fun getData(handler: (ArrayList<FruitDTO>) -> Unit) {
        requestStartTime = System.currentTimeMillis()
        var gson = Gson()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                var requestTime = System.currentTimeMillis() - requestStartTime
                Log.i("FruitListClient", "$requestTime")
                successfulRequest(requestTime)
                val json = response.getJSONArray("fruit").toString()
                val string: String = response.toString()
                val fruitDtoType = object : TypeToken<ArrayList<FruitDTO>>() {}.type
                var fruitDtoList: ArrayList<FruitDTO> = gson.fromJson(json, fruitDtoType)
                Log.i("FruitListClient", "Invoked")
                handler.invoke(fruitDtoList)

            }, { _ ->
                Log.e("FruitListClient", "Error")
            }
        )
        queue.add(jsonObjectRequest)

    }
    private fun successfulRequest(data: Long) {
        var url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=load&data=$data"
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String>
            { response ->
                var strResp = response.toString()
                Log.d("FruitListClient", strResp)
            },
            Response.ErrorListener {Log.d("FruitListClient", "SuccessRequest didn't work") })
        queue.add(stringReq)
    }

    override fun viewLoaded(data: Long) {
        var url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=display&data=$data"
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String>
            { response ->
                var strResp = response.toString()
                Log.d("FruitListClient", strResp)
            },
            Response.ErrorListener {Log.d("FruitListClient", "viewLoaded didn't work") })
        queue.add(stringReq)
    }
}