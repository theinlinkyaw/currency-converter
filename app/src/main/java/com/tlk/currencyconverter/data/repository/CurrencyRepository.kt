package com.tlk.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.tlk.currencyconverter.data.local.dao.CurrencyDao
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.remote.ApiDataSource
import com.tlk.currencyconverter.util.Resource
import com.tlk.currencyconverter.util.performDataLoad
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val dataSource: ApiDataSource,
    private val currencyDao: CurrencyDao
) {
    fun getCurrencies(): LiveData<Resource<List<Currency>>> = performDataLoad(
        loadFromDb = { currencyDao.currenciesLiveData() },
        networkCall = { dataSource.getCurrencies() },
        saveCallResult = { currencyDao.insertAllCurrencies(it) }
    ) {
        it == null || it.isEmpty()
    }
}