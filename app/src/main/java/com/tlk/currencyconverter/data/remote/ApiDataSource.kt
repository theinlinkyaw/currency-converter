package com.tlk.currencyconverter.data.remote

import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote

interface ApiDataSource {

    suspend fun getCurrencies(): List<Currency>

    suspend fun getQuotes(): List<Quote>
}