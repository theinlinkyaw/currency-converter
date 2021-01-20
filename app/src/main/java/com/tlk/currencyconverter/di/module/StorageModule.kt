package com.tlk.currencyconverter.di.module

import android.content.Context
import com.tlk.currencyconverter.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDatabase) = db.currencyDao()

    @Singleton
    @Provides
    fun provideQuoteDao(db: AppDatabase) = db.quoteDao()
}