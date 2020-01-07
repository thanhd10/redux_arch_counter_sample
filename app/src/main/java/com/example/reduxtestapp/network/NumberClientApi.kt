package com.example.reduxtestapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var NUMBER_API_URL = "http://numbersapi.com/"

fun provideNumberClient(): NumberFactsApi =
    Retrofit.Builder()
        .baseUrl(NUMBER_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(NumberFactsApi::class.java)
