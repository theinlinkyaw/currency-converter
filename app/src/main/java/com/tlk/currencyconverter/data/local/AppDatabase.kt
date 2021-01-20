package com.tlk.currencyconverter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tlk.currencyconverter.data.local.dao.CurrencyDao
import com.tlk.currencyconverter.data.local.dao.QuoteDao
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote

@Database(entities = [Currency::class, Quote::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: create(context).also { INSTANCE = it }
            }
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "dms_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}