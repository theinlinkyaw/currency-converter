package com.tlk.currencyconverter.data.remote

import com.tlk.currencyconverter.data.remote.response.ListResponse
import com.tlk.currencyconverter.data.remote.response.LiveResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun getSupportedCurrencies(@Query("access_key") key: String): ListResponse

    @GET("live")
    suspend fun getCurrencyLive(@Query("access_key") key: String): LiveResponse

}