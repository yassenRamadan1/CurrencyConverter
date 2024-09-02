package org.example.currency_converter.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.currency_converter.data.remote.model.ApiResponse
import org.example.currency_converter.data.remote.model.Currency
import org.example.currency_converter.data.remote.model.RequestState

class CurrencyApiServiceImp: CurrencyApiService {
    companion object {
        const val API_KEY = "cur_live_seGDRO7QhuQrv76Unsd847O580mWeDxl5xocQI40"
        const val ENDPOINT = "https://api.currencyapi.com/v3/latest"
    }
    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout){
            requestTimeoutMillis = 15000
        }
        install(DefaultRequest){
            headers.append("apikey", API_KEY)
        }
    }

    override suspend fun getExchangeRate(): RequestState<List<Currency>> {
        val response = httpClient.get(ENDPOINT)
        return  try {
            if (response.status.value == 200){
                val apiResponse = Json.decodeFromString<ApiResponse>(response.body())
                 RequestState.Success(apiResponse.data.values.toList())
            }else {
                 RequestState.Error("Error fetching data")
            }
        }catch (e: Exception){
             RequestState.Error(e.message ?: "An error occurred")
        }
    }
}