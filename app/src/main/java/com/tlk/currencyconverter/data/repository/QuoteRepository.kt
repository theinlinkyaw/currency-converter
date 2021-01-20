package com.tlk.currencyconverter.data.repository

import androidx.lifecycle.LiveData
import com.tlk.currencyconverter.data.local.dao.QuoteDao
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote
import com.tlk.currencyconverter.data.remote.ApiDataSource
import com.tlk.currencyconverter.util.RateLimiter
import com.tlk.currencyconverter.util.Resource
import com.tlk.currencyconverter.util.performDataLoad
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val quoteDao: QuoteDao
) {
    private var currency: Currency? = null
    private var amount: Double? = 1.0

    private val quoteRateLimit = RateLimiter(10, TimeUnit.MINUTES)

    fun getQuotes(currency: Currency, amount: Double): LiveData<Resource<List<Quote>>> {
        this.currency = currency
        this.amount = amount

        return performDataLoad(
            loadFromDb = { loadFromDb() },
            networkCall = { apiDataSource.getQuotes() },
            saveCallResult = { quoteDao.insertAllQuotes(it) }
        ) {
            it == null || it.isEmpty() || quoteRateLimit.shouldFetch()
        }
    }

    private fun loadFromDb(): List<Quote> {
        val result = mutableListOf<Quote>()
        val quotes = quoteDao.quotes()
        if (quotes.isNotEmpty()) {
            quotes.find { it.other == currency!!.code }
                ?.let { that ->
                    val rate = amount!! / that.exchangeRate

                    quotes.forEach {
                        if (it.source == that.source && it.other != that.other) {
                            result.add(
                                Quote(
                                    that.other.plus(it.other),
                                    that.other,
                                    it.other,
                                    exchangeRate(rate * it.exchangeRate)
                                )
                            )
                        }
                    }
                }
        }

        return result.toList()
    }

    private fun exchangeRate(double: Double): Double {
        var d = double
        var i = 0
        while(d < 0.1) {
            d *= 10
            i++
        }
        return BigDecimal(double).setScale(i + 2, RoundingMode.HALF_EVEN).toDouble()
    }
}