package com.tlk.currencyconverter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tlk.currencyconverter.data.model.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM Currency")
    suspend fun currenciesLiveData(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(currencies: List<Currency>)
}