package com.tlk.currencyconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey
    val currencyPair: String,
    val source: String,
    val other: String,
    val exchangeRate: Double
)