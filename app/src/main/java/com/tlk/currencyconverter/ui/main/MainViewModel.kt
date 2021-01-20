package com.tlk.currencyconverter.ui.main

import androidx.lifecycle.*
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote
import com.tlk.currencyconverter.data.repository.CurrencyRepository
import com.tlk.currencyconverter.data.repository.QuoteRepository
import com.tlk.currencyconverter.di.scope.ActivityScope
import com.tlk.currencyconverter.util.Resource
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val input: MediatorLiveData<Pair<Currency, Double>> = MediatorLiveData()

    private val currency: MutableLiveData<Currency> = MutableLiveData()

    private val amount: MutableLiveData<Double> = MutableLiveData()

    init {
        input.addSource(currency) { currency ->
            amount.value?.let { amount ->
                input.value = Pair(currency, amount)
            }
        }
        input.addSource(amount) { amount ->
            currency.value?.let { currency ->
                input.value = Pair(currency, amount)
            }
        }
    }

    val currencies: LiveData<Resource<List<Currency>>> = currencyRepository.getCurrencies()

    val quotes: LiveData<Resource<List<Quote>>> = input.switchMap {
        quoteRepository.getQuotes(it.first, it.second)
    }

    fun changeCurrency(currency: Currency) {
        this.currency.value = currency
    }

    fun changeAmount(amount: Double) {
        this.amount.value = amount
    }

}