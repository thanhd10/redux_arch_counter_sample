package com.example.reduxtestapp.network

import com.example.reduxtestapp.network.model.NumberFactResponse
import retrofit2.Call
import retrofit2.http.GET

interface NumberFactsApi {

    @GET("random/year?json")
    fun getRandomYearFact(): Call<NumberFactResponse>
}