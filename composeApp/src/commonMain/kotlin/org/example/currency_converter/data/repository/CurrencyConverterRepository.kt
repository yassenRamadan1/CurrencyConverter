package org.example.currency_converter.data.repository

import org.example.currency_converter.data.remote.model.Currency
import org.example.currency_converter.data.remote.model.RequestState

interface CurrencyConverterRepository {
    fun getLastExchangeRate() : RequestState<List<Currency>>
}