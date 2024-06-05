package com.example.artisticanthem.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://poetrydb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val poetryApiService: PoetryApiService = retrofit.create(PoetryApiService::class.java)
}
