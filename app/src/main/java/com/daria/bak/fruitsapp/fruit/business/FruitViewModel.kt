package com.daria.bak.fruitsapp.fruit.business

import android.icu.text.MeasureFormat
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import android.icu.util.ULocale
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.daria.bak.fruitsapp.fruit.data.FruitClientInterface

class FruitViewModel(private val client: FruitClientInterface): ViewModel() {
    var type: String = ""
    lateinit var price: String
    lateinit var weight: String

    @RequiresApi(Build.VERSION_CODES.N)
    fun setFruitData(type: String, price: Float, weight: Float) {
        this.type = type
        val format = MeasureFormat.getInstance(ULocale.UK, MeasureFormat.FormatWidth.WIDE)
        val pounds = (price/100).toInt()
        val pence = (price - pounds*100).toInt()
        this.price = "$pounds pound $pence pence"
        val weightMeasure = Measure(weight/1000, MeasureUnit.KILOGRAM)
        this.weight = format.format((weightMeasure))
    }

    fun sendViewAnalytics(time: Long) {
        client.sendViewAnalytics(time)
    }
}