package com.example.projectmoneymate.data.remote

import com.google.gson.annotations.SerializedName

data class RatesResponse(
    val result: String,
    @SerializedName("base_code") val baseCode: String,
    @SerializedName("time_last_update_utc") val lastUpdate: String,
    val rates: Map<String, Double>
)