package com.tlk.currencyconverter.data.remote

import android.content.Context
import com.tlk.currencyconverter.R
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    val context: Context,
    private val apiService: ApiService
) : ApiDataSource{

    override suspend fun getCurrencies(): List<Currency> {
        return apiService.getSupportedCurrencies(
            context.getString(R.string.currency_layer_access_key)
        ).currencies
    }

    override suspend fun getQuotes(): List<Quote> {
        return apiService.getCurrencyLive(
            context.getString(R.string.currency_layer_access_key)
        ).quotes
    }
}