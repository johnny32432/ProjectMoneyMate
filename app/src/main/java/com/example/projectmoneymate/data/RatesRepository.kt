package com.example.projectmoneymate.data

import com.example.projectmoneymate.data.remote.RatesApi
import com.example.projectmoneymate.data.remote.RetrofitClient
import java.io.IOException

data class RatesResult(
    val rates: List<CurrencyRate>,
    val updated: String
)

class RatesRepository(
    private val api: RatesApi = RetrofitClient.api
) {

    private val currencies = listOf(
        Triple("USD", "US Dollar", "us"),
        Triple("EUR", "Euro", "eu"),
        Triple("RUB", "Russian Ruble", "ru"),
        Triple("CNY", "Chinese Yuan", "cn"),
        Triple("GBP", "British Pound", "gb"),
        Triple("TRY", "Turkish Lira", "tr"),
        Triple("KGS", "Kyrgyz Som", "kg"),
        Triple("UZS", "Uzbek Som", "uz"),
        Triple("JPY", "Japanese Yen", "jp"),
        Triple("AED", "UAE Dirham", "ae")
    )

    suspend fun getRates(): RatesResult {
        val response = api.getLatestRates("KZT")

        if (response.result != "success")
            throw IOException("API returned an error")

        val rates = currencies.mapNotNull { (code, name, country) ->
            val rate = response.rates[code]

            if (rate == null || rate == 0.0) {
                null
            } else {
                CurrencyRate(
                    code = code,
                    name = name,
                    flagUrl = "https://flagcdn.com/w80/$country.png",
                    kztPerUnit = 1 / rate
                )
            }
        }

        return RatesResult(rates, response.lastUpdate)
    }
}