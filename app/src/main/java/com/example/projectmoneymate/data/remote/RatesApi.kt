package com.example.projectmoneymate.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RatesApi {

    // GET https://open.er-api.com/v6/latest/KZT
    @GET("v6/latest/{base}")
    suspend fun getLatestRates(
        @Path("base") base: String
    ): RatesResponse
}