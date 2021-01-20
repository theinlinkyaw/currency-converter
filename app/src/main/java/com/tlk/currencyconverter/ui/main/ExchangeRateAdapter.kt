package com.tlk.currencyconverter.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tlk.currencyconverter.data.model.Quote

class ExchangeRateAdapter(context: Context, private val exchangeRates: MutableList<Quote>) : RecyclerView.Adapter<ExchangeRateViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        return ExchangeRateViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return exchangeRates.size
    }

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        holder.bind(exchangeRates[position])
    }

}