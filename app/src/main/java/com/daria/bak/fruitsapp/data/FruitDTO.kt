package com.daria.bak.fruitsapp.data

import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.annotations.SerializedName

class FruitDTO {
    @SerializedName("type")
    var type: String = ""

    @SerializedName("price")
    var price: Float = 0f

    @SerializedName("weight")
    var weight: Float = 0f
}