package org.example.currency_converter.data.remote.api

import org.example.currency_converter.data.remote.model.Currency
import org.example.currency_converter.data.remote.model.RequestState

interface CurrencyApiService {
    suspend fun getExchangeRate(): RequestState<List<Currency>>
}