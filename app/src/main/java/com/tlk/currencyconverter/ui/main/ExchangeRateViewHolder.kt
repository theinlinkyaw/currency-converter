package com.tlk.currencyconverter.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tlk.currencyconverter.R
import com.tlk.currencyconverter.data.model.Quote
import kotlinx.android.synthetic.main.list_item_exchange_rate.view.*

class ExchangeRateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    layoutInflater.inflate(R.layout.list_item_exchange_rate, parent, false)
) {

    fun bind(data: Quote) {
        itemView.currencyTextView.text = data.other
        itemView.exchangeRateTextView.text = data.exchangeRate.toBigDecimal().toPlainString()
    }

}