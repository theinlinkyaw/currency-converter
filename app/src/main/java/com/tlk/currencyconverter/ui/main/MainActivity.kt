package com.tlk.currencyconverter.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.tlk.currencyconverter.MyApplication
import com.tlk.currencyconverter.R
import com.tlk.currencyconverter.data.model.Currency
import com.tlk.currencyconverter.data.model.Quote
import com.tlk.currencyconverter.util.Resource.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainComponent: MainComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    private val currencies: MutableList<Currency> = mutableListOf()

    private var currencyAdapter: CurrencyAdapter? = null

    private val exchangeRates: MutableList<Quote> = mutableListOf()

    private var exchangeRateAdapter: ExchangeRateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as MyApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupCurrencyDropdown()
        setupAmountTextField()
        setupExchangeRateRecyclerView()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.currencies.observe(this) { resource ->
            when(resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        currencies.clear()
                        currencies.addAll(data)
                        currencyAdapter?.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        }

        mainViewModel.quotes.observe(this) { resource ->
            when(resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        exchangeRates.clear()
                        exchangeRates.addAll(data)
                        exchangeRateAdapter?.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        }
    }

    private fun setupCurrencyDropdown() {
        currencyAdapter = CurrencyAdapter(this, R.layout.list_item_currency, currencies)

        currencyDropdown.setAdapter(currencyAdapter)

        currencyDropdown.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val currency = currencyAdapter?.getItem(i)
                mainViewModel.changeCurrency(currency!!)
            }
    }

    private fun setupAmountTextField() {
        amountTextField.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    mainViewModel.changeAmount(v.text.toString().toDouble())
                    false
                }
                else -> false
            }
        }
    }

    private fun setupExchangeRateRecyclerView() {
        exchangeRateAdapter = ExchangeRateAdapter(this, exchangeRates)

        exchangeRatesRecyclerView.adapter = exchangeRateAdapter
        exchangeRatesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}