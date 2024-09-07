package org.example.currency_converter.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import org.example.currency_converter.data.remote.api.CurrencyApiService
import org.example.currency_converter.data.remote.api.CurrencyApiServiceImp
import org.example.currency_converter.data.repository.CurrencyConverterRepositoryImp

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {
            CurrencyApiServiceImp().getExchangeRate()
        }
    }

}