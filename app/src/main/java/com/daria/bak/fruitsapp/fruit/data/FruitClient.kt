package com.daria.bak.fruitsapp.fruit.data

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest

interface FruitClientInterface {
    fun sendViewAnalytics(data: Long)
}
class FruitClient(private val queue: RequestQueue): FruitClientInterface {

    override fun sendViewAnalytics(data: Long) {
        val time = data.toInt()
        val url =
            "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats?event=display&data=$time"
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                val strResp = response.toString()
                Log.d("FruitClient", strResp)
            },
            { Log.d("FruitClient", "viewLoaded didn't work") })

        queue.add(stringReq)
    }
}