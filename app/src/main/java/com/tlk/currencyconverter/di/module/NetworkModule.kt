package com.tlk.currencyconverter.di.module

import com.squareup.moshi.Moshi
import com.tlk.currencyconverter.data.adapter.CurrencyAdapter
import com.tlk.currencyconverter.data.adapter.QuoteAdapter
import com.tlk.currencyconverter.data.remote.ApiDataSource
import com.tlk.currencyconverter.data.remote.ApiDataSourceImpl
import com.tlk.currencyconverter.data.remote.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(moshi: Moshi): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://api.currencylayer.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideConverterFactory(): Moshi {
        return Moshi.Builder()
            .add(CurrencyAdapter())
            .add(QuoteAdapter())
            .build()
    }

    @Provides
    fun provideApiDateSource(apiDataSourceImpl: ApiDataSourceImpl): ApiDataSource {
        return apiDataSourceImpl
    }
}