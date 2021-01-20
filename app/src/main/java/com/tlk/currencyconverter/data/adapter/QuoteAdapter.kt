package com.tlk.currencyconverter.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.tlk.currencyconverter.data.model.Quote

class QuoteAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): List<Quote> {
        val quotes: MutableList<Quote> = mutableListOf()

        reader.beginObject()
        var currencyPair = ""
        var source = ""
        var other = ""
        var exchangeRate = 0.0
        while (reader.hasNext()) {
            currencyPair = reader.nextName()
            source = currencyPair.substring(0, 3)
            other = currencyPair.substring(3)
            exchangeRate = reader.nextDouble()
            quotes.add(Quote(currencyPair, source, other, exchangeRate))
        }
        reader.endObject()

        return quotes
    }
}