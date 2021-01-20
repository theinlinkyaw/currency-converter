package com.tlk.currencyconverter.data.remote.response

import com.squareup.moshi.Json
import com.tlk.currencyconverter.data.model.Currency

data class ListResponse(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "terms")
    val terms: String,
    @Json(name = "privacy")
    val privacy: String,
    @Json(name = "historical")
    val historical: Boolean,
    @Json(name = "date")
    val date: String,
    @Json(name = "timestamp")
    val timestamp: Long,
    @Json(name = "source")
    val source: String,
    @Json(name = "currencies")
    val currencies: List<Currency>
)