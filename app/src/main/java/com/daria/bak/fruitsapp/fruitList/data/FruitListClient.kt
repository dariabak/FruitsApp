package com.daria.bak.fruitsapp.fruitList.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.daria.bak.fruitsapp.fruitList.business.Fruit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface FruitListClientInterface {
    fun getFruitList(handler: (Result<ArrayList<Fruit>>) -> Unit)
    fun sendViewLoadingAnalytics(data: Long)
    fun sendErrorAnalytics()
}
class FruitListClient(private var queue: RequestQueue): FruitListClientInterface {

    override fun getFruitList(handler: (Result<ArrayList<Fruit>>) -> Unit) {
        val requestStartTime = System.currentTimeMillis()
        val gson = Gson()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val requestTime = System.currentTimeMillis() - requestStartTime
                Log.i("FruitListClient", "$requestTime")
                successfulRequest(requestTime)
                val json = response.getJSONArray("fruit").toString()
                val fruitDtoType = object : TypeToken<ArrayList<FruitDTO>>() {}.type
                val fruitListDTO: ArrayList<FruitDTO> = gson.fromJson(json, fruitDtoType)
                val fruitList = fruitListDTO.map { fruitDTO -> Fruit(fruitDTO)} as ArrayList<Fruit>
                Log.i("FruitListClient", "getFruitListClient invoked")
                handler.invoke(Result.success(fruitList))

            }, { error ->
                handler.invoke(Result.failure(error))
                Log.e("FruitListClient", "Error")
            }
        )
        queue.add(jsonObjectRequest)
    }

    private fun successfulRequest(data: Long) {
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=load&data=$data"
        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                val strResp = response.toString()
                Log.d("FruitListClient", strResp)
            },
            {Log.d("FruitListClient", "SuccessRequest didn't work") })
        queue.add(stringReq)
    }

    override fun sendViewLoadingAnalytics(data: Long) {
        val time = data.toInt()
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=display&data=$time"
        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                val strResp = response.toString()
                Log.d("FruitListClient", strResp)
            },
            {Log.d("FruitListClient", "viewLoaded didn't work") })
        queue.add(stringReq)
    }

    override fun sendErrorAnalytics() {
        val url = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=error&data="
        val stringReq = StringRequest(Request.Method.GET, url,
            { response ->
                val strResp = response.toString()
                Log.d("FruitListClient", strResp)
            },
            {Log.d("FruitListClient", "viewLoaded didn't work") })
        queue.add(stringReq)
    }

}