package com.tlk.currencyconverter.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.tlk.currencyconverter.data.model.Currency

class CurrencyAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): List<Currency> {
        val currencies: MutableList<Currency> = mutableListOf()

        reader.beginObject()
        var code = ""
        var name = ""
        while (reader.hasNext()) {
            code = reader.nextName()
            name = reader.nextString()
            currencies.add(Currency(code, name))
        }
        reader.endObject()

        return currencies
    }
}