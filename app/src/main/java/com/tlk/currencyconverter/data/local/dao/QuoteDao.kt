package com.tlk.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tlk.currencyconverter.data.model.Quote

@Dao
interface QuoteDao {
    @Query("SELECT * FROM Quote")
    fun quotes(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(quotes: List<Quote>)
}