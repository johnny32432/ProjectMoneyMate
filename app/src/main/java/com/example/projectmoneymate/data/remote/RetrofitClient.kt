package com.example.projectmoneymate.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://open.er-api.com/"

    val api: RatesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON -> data class
            .build()
            .create(RatesApi::class.java)
    }
}