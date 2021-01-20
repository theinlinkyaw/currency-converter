package com.tlk.currencyconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey
    val code: String,
    val name: String
) {
    override fun toString(): String {
        return "$name ($code)"
    }
}
