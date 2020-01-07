package com.example.reduxtestapp.network.model

import com.google.gson.annotations.SerializedName

data class NumberFactResponse(
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("number")
    var number: Long = 0,
    @SerializedName("found")
    var found: Boolean = true,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("date")
    var date: String? = null
)